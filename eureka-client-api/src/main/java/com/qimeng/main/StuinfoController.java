package com.qimeng.main;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.StudentInfo;
import com.qimeng.main.oldStuService.OldService;
import com.qimeng.main.service.StuInfoService;
import com.qimeng.main.service.StudentInfoService;
import com.qimeng.main.vo.DeviceStatusVo;
import com.qimeng.main.vo.RecycleLogVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentBindVo;
import com.qimeng.main.vo.StudentInfoVo;

@RestController
public class StuinfoController {
	private static Logger logger = Logger.getLogger(StuinfoController.class);
	@Autowired
	OldService oldService;

	@Autowired
	StudentInfoService studentInfoService;
	@Autowired
	StuInfoService stuInfoService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/stuinfo/getstudent")
	public String getStudent(@RequestBody JSONObject message) {

		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});
		logger.info(requestMessage.toString());
		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		if (stuInfoVo.getStuCode().length() > 16 ||stuInfoVo.getStuCard().length() > 16) {
			stuInfoVo.setStuCode(stuInfoVo.getStuCode().substring(0,16));
			stuInfoVo.setStuCard(stuInfoVo.getStuCard().substring(0,16));
			message.put("data", stuInfoVo);
			logger.info("************二维码转换***************");
			logger.info(message.toJSONString());
		}
		
		logger.info("************获取学生信息***************");
		String responseString = null;
		try {
			responseString = stuInfoService.getStudent(message);
			logger.info(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取学生信息接口错误");
		}

		StudentInfo studentInfo = studentInfoService.getStudentByCodeCard(stuInfoVo.getStuCode(), stuInfoVo.getStuCard());
		
		if (studentInfo == null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("找不到当前用户");
			logger.info(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
		String accountTonken = RandomStringUtils.randomAlphanumeric(8);

		// 赋值操作密钥
		if (responseString != null) {
			JSONObject json = JSONObject.parseObject(responseString);
			if (json.getBoolean("success") && !StringUtils.isEmpty(json.getString("accountTonken"))) {
				accountTonken = json.getString("accountTonken");
			}
		}

		stuInfoVo.setName(studentInfo.getName());
		stuInfoVo.setActive(1);
		stuInfoVo.setBind(studentInfo.getBind() > 0 ? 1 : 0);
		stuInfoVo.setPoint(studentInfo.getTotalPoints().intValue());
		stuInfoVo.setQrCode("http://o0c.cn/?code=" + studentInfo.getStuCode() + "&card=" + studentInfo.getStuCard());
		stuInfoVo.setStuCard(studentInfo.getStuCard());
		stuInfoVo.setStuCode(studentInfo.getStuCode());

		ResponseMessage<StudentInfoVo> responseMessage = new ResponseMessage<StudentInfoVo>();
		responseMessage.setData(stuInfoVo);
		responseMessage.setAccountTonken(accountTonken);
		responseMessage.setSuccessMessage("获取当前用户成功");
		logger.info(responseMessage.toString());
		return JSONObject.toJSONString(responseMessage);
	}

	@RequestMapping("/stuinfo/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message) throws Exception {
		
		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});
		logger.info(requestMessage.toString());
		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		if (stuInfoVo.getStuCode().length() > 16 ||stuInfoVo.getStuCard().length() > 16) {
			stuInfoVo.setStuCode(stuInfoVo.getStuCode().substring(0,16));
			stuInfoVo.setStuCard(stuInfoVo.getStuCard().substring(0,16));
			message.put("data", stuInfoVo);
			logger.info("************二维码转换***************");
			logger.info(message.toJSONString());
		}
		
		
		logger.info("************上传积分***************");
		String responseString = null;
		try {
			responseString = stuInfoService.upLoadPoints(message);
			logger.info(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取学生上传积分接口错误");
		}


		// logger.info(oldService.uploadIntegral(stuInfo,
		// requestMessage.getMachineID()));
		StudentInfo temp = studentInfoService.getStudentByCodeCardQR(stuInfoVo.getStuCode(), stuInfoVo.getStuCard());
		String jsonString = oldService.newuploadIntegral(stuInfoVo, requestMessage.getMachineID(),temp.getStuCode());
		logger.info("新后台上传积分：" + jsonString);

		ResponseMessage<String> responseMessage = new ResponseMessage<String>();

		if ("success".equals(jsonString)) {

			responseMessage.setData("");
			responseMessage.setSuccessMessage("积分上传成功");
		} else {
			responseMessage.setData("");
			responseMessage.setFailedMessage("积分上传失败");
		}
		logger.info(responseMessage.toString());
		return JSONObject.toJSONString(responseMessage);
	}

	@RequestMapping("/devinfo/updatemachineid")
	public String updateMachineID(@RequestBody JSONObject message) {
		logger.info("************更新机器编码***************");
		String responseString = null;
		try {
			responseString = stuInfoService.updateMachineID(message);
			logger.info(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取更新机器码接口错误");
		}

		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {
				});
		logger.info(requestMessage.toString());

		DeviceStatusVo machineInfo = (DeviceStatusVo) requestMessage.getData();
		// logger.info(oldService.updateJqm(machineInfo,
		// requestMessage.getMachineID()));

		ResponseMessage<String> responseMessage = new ResponseMessage<String>();

		if (studentInfoService.updateMachineID(requestMessage.getMachineID(), machineInfo.getSerialNumber()) != 0) {
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新成功");
		} else {
			responseMessage.setData("");
			responseMessage.setFailedMessage("更新失败");
		}
		logger.info(responseMessage.toString());
		return JSONObject.toJSONString(responseMessage);
	}

	@RequestMapping("/stuinfo/stubindbyphone")
	public String stuBindByPhone(@RequestBody JSONObject message) throws Exception {
		logger.info("************手机号绑定学生***************");
		String responseString = null;
		try {
			responseString = stuInfoService.stuBindByPhone(message);
			logger.info(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("手机号绑定学生接口错误");
		}

		RequestMessage<StudentBindVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentBindVo>>() {
				});
		logger.info(requestMessage.toString());

		if (!stringRedisTemplate.hasKey("WechatKey::" + requestMessage.getAccountTonken())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("AccountTonken已过期请重新登陆");
			return JSONObject.toJSONString(responseMessage);
		}

		JSONObject jsonwechat = JSONObject
				.parseObject(stringRedisTemplate.opsForValue().get("WechatKey::" + requestMessage.getAccountTonken()));

		StudentBindVo stubindVo = (StudentBindVo) requestMessage.getData();

		StudentInfo studentInfo = studentInfoService.getStudentByPhone(stubindVo.getPhone());
		if (studentInfo == null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("找不到当前用户");
			logger.info(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
		if (studentInfo.getBind() > 0) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("该学生已经绑定");
			logger.info(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}

		String response = oldService.bindstu(jsonwechat.getString("openid"), jsonwechat.getString("unionid"),
				stubindVo.getPhone());
		logger.info("绑定返回信息:" + response);
		if (!"success".equals(response)) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("绑定失败");
			logger.info(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}

		StudentInfoVo stuInfoVo = new StudentInfoVo();
		stuInfoVo.setName(studentInfo.getName());
		stuInfoVo.setActive(1);
		stuInfoVo.setBind(studentInfo.getBind() > 0 ? 1 : 0);
		stuInfoVo.setPoint(studentInfo.getTotalPoints().intValue());
		stuInfoVo.setQrCode("http://o0c.cn/?code=" + studentInfo.getStuCode() + "&card=" + studentInfo.getStuCard());
		stuInfoVo.setStuCard(studentInfo.getStuCard());
		stuInfoVo.setStuCode(studentInfo.getStuCode());

		ResponseMessage<StudentInfoVo> responseMessage = new ResponseMessage<StudentInfoVo>();
		responseMessage.setData(stuInfoVo);
		responseMessage.setSuccessMessage("获取当前用户成功");
		logger.info(responseMessage.toString());
		return JSONObject.toJSONString(responseMessage);
	}

	@RequestMapping("/stuinfo/getrecyclelog")
	public String getRecycleLog(@RequestBody JSONObject message) {
		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});
		logger.info(requestMessage.toString());
		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		if (stuInfoVo.getStuCode().length() > 16 ||stuInfoVo.getStuCard().length() > 16) {
			stuInfoVo.setStuCode(stuInfoVo.getStuCode().substring(0,16));
			stuInfoVo.setStuCard(stuInfoVo.getStuCard().substring(0,16));
			message.put("data", stuInfoVo);
			logger.info("************二维码转换***************");
			logger.info(message.toJSONString());
		}
		
		logger.info("************学生投递历史接口***************");
		String responseString = null;
		try {
			responseString = stuInfoService.getRecycleLog(message);
			logger.info(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学生投递历史接口错误");
		}

		
		StudentInfo studentInfo  = studentInfoService.getStudentByCodeCard(stuInfoVo.getStuCode(), stuInfoVo.getStuCard());
		
		if (studentInfo == null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("找不到当前用户");
			logger.info(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
		List<RecycleLogVo> list = studentInfoService.getRecycleLog(studentInfo.getId());

		ResponseMessage<List<RecycleLogVo>> responseMessage = new ResponseMessage<List<RecycleLogVo>>();
		responseMessage.setData(list);
		responseMessage.setSuccessMessage("获取日志信息成功");
		logger.info(responseMessage.toString());
		return JSONObject.toJSONString(responseMessage);
	}
}
