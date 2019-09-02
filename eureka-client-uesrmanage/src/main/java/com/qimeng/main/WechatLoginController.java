package com.qimeng.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.MainToStudent;
import com.qimeng.main.entity.UserMain;
import com.qimeng.main.entity.WecharToMain;
import com.qimeng.main.entity.WecharUser;
import com.qimeng.main.service.MainToStudentService;
import com.qimeng.main.service.UserMainService;
import com.qimeng.main.service.WechatToMainService;
import com.qimeng.main.service.WechatUserService;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StuInformVo;
import com.qimeng.main.wechat.WeChatService;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月30日 上午11:38:39
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
@RequestMapping("/wechat")
public class WechatLoginController {

	private static Logger logger = Logger.getLogger(WechatLoginController.class);
	@Autowired
	WeChatService weChatService;
	@Autowired
	WechatUserService wechatUserService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	WechatToMainService wechatToMainService;
	@Autowired
	MainToStudentService mainToStudentService;
	@Autowired
	UserMainService userMainService;

	@RequestMapping("/login")
	public String getLogin(@RequestBody JSONObject message) {
		RequestMessage<String> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<String>>() {
				});
		String wechatStrig = (String) requestMessage.getData();
		logger.debug(wechatStrig);
		try {
			JSONObject jsonObject = JSONObject.parseObject(wechatStrig);
			String wechatid = weChatService.getCode2Session(jsonObject.getString("code"));

			JSONObject jsonwechat = JSONObject.parseObject(wechatid);
			WecharUser wecharUser = wechatUserService.selectWecharUserByOpenidOrUnionid(jsonwechat.getString("openid"),
					jsonwechat.getString("unionid"));
			if (wecharUser == null) {
				Date date = new Date();
				wecharUser = new WecharUser();
				wecharUser.setOpenid(jsonwechat.getString("openid"));
				wecharUser.setUnionid(jsonwechat.getString("unionid"));
				wecharUser.setCreateTime(date);
				wecharUser.setUpdateTime(date);
				wechatUserService.insertWecharUser(wecharUser);
			}
			String accountTonken = RandomStringUtils.randomAlphanumeric(8);
			while (stringRedisTemplate.hasKey("WechatKey::" + accountTonken)) {
				accountTonken = RandomStringUtils.randomAlphanumeric(8);
			}
			stringRedisTemplate.opsForValue().set("WechatKey::" + accountTonken, jsonwechat.toJSONString(), 1L,
					TimeUnit.HOURS);
			WecharToMain wecharToMain = wechatToMainService.selecWechatToMainByOpendIdAndUnionId(wecharUser.getOpenid(),
					wecharUser.getUnionid());
			if (wecharToMain == null) {
				List<StuInformVo> list = new ArrayList<StuInformVo>();
				ResponseMessage<List<StuInformVo>> responseMessage = new ResponseMessage<List<StuInformVo>>();
				responseMessage.setData(list);
				responseMessage.setAccountTonken(accountTonken);
				responseMessage.setSuccessMessage("登陆成功");
				return JSONObject.toJSONString(responseMessage);
			}
			MainToStudent mainToStudent = new MainToStudent();
			mainToStudent.setUuid(wecharToMain.getUuid());
			mainToStudent = mainToStudentService.selectMainToStudent(mainToStudent);
			if (mainToStudent == null) {
				List<StuInformVo> list = new ArrayList<StuInformVo>();
				ResponseMessage<List<StuInformVo>> responseMessage = new ResponseMessage<List<StuInformVo>>();
				responseMessage.setData(list);
				responseMessage.setAccountTonken(accountTonken);
				responseMessage.setSuccessMessage("登陆成功");
				return JSONObject.toJSONString(responseMessage);
			}
			List<StuInformVo> list = JSON.parseObject(mainToStudent.getStuinfo(),
					new TypeReference<List<StuInformVo>>() {
					});
			ResponseMessage<List<StuInformVo>> responseMessage = new ResponseMessage<List<StuInformVo>>();
			responseMessage.setData(list);
			responseMessage.setAccountTonken(accountTonken);
			responseMessage.setSuccessMessage("登陆成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/phone")
	public String getPhone(@RequestBody JSONObject message) {
		RequestMessage<String> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<String>>() {
				});
		String wechatStrig = (String) requestMessage.getData();
		logger.debug(wechatStrig);
		try {
			if (!stringRedisTemplate.hasKey("WechatKey::" + requestMessage.getAccountTonken())) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("AccountTonken已过期请重新登陆");
				return JSONObject.toJSONString(responseMessage);
			}

			JSONObject jsonwechat = JSONObject.parseObject(
					stringRedisTemplate.opsForValue().get("WechatKey::" + requestMessage.getAccountTonken()));
			JSONObject jsonObject = JSONObject.parseObject(wechatStrig);
			String wechatphone = weChatService.getPhoneNumber(jsonObject.getString("encryptedData"),
					jsonwechat.getString("session_key"), jsonObject.getString("iv"));
			logger.debug(wechatphone);

			UserMain userMain = userMainService.selectUserMainByPhone(wechatphone);
			if (userMain == null) {
				Date date = new Date();
				userMain = new UserMain();
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				userMain.setUuid(uuid);
				userMain.setPhone(wechatphone);
				userMain.setCreateTime(date);
				userMain.setUpdateTime(date);
				userMainService.insertUserMain(userMain);
			}
			WecharToMain wecharToMain = wechatToMainService.selecWechatToMainByOpendIdAndUnionId(
					jsonwechat.getString("openid"), jsonwechat.getString("unionid"));
			if (wecharToMain == null) {
				wecharToMain = new WecharToMain();
				wecharToMain.setOpenid(jsonwechat.getString("openid"));
				wecharToMain.setUnionid(jsonwechat.getString("unionid"));
				wecharToMain.setUuid(userMain.getUuid());
				wechatToMainService.inserWechatToMain(wecharToMain);
			}
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData(wechatphone);
			responseMessage.setSuccessMessage("获取电话号码成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/bindstu")
	public String bindStudent(@RequestBody JSONObject message) {
		RequestMessage<String> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<String>>() {
				});
		String wechatStrig = (String) requestMessage.getData();
		logger.debug(wechatStrig);
		try {
			if (!stringRedisTemplate.hasKey("WechatKey::" + requestMessage.getAccountTonken())) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("AccountTonken已过期请重新登陆");
				return JSONObject.toJSONString(responseMessage);
			}

			JSONObject jsonwechat = JSONObject.parseObject(
					stringRedisTemplate.opsForValue().get("WechatKey::" + requestMessage.getAccountTonken()));
			
			WecharUser wecharUser = wechatUserService.selectWecharUserByOpenidOrUnionid(jsonwechat.getString("openid"),
					jsonwechat.getString("unionid"));
			WecharToMain wecharToMain = wechatToMainService.selecWechatToMainByOpendIdAndUnionId(wecharUser.getOpenid(),
					wecharUser.getUnionid());
			MainToStudent mainToStudent = new MainToStudent();
			mainToStudent.setUuid(wecharToMain.getUuid());
			mainToStudent.setStuinfo(wechatStrig);
			mainToStudentService.insertMainToStudent(mainToStudent);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("绑定成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
}
