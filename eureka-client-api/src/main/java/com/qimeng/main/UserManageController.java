package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.service.UserManageService;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月2日 下午4:21:58 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/usermanage")
public class UserManageController {
	private static Logger logger = Logger.getLogger(UserManageController.class);
	@Autowired
	UserManageService userManageService;
	
	@RequestMapping("/wechat/login")
	public String getLogin(@RequestBody JSONObject message) {
		logger.info("************微信登陆***************");
		try {
			String responseString = userManageService.getLogin(message);
			logger.info(responseString);
			return responseString;
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("获取微信登陆接口错误");
			logger.error(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/wechat/phone")
	public String getPhone(@RequestBody JSONObject message) {
		logger.info("************获取电话号码***************");
		try {
			String responseString = userManageService.getPhone(message);
			logger.info(responseString);
			return responseString;
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("获取电话号码接口错误");
			logger.error(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/wechat/bindstu")
	public String bindStudent(@RequestBody JSONObject message) {
		logger.info("************绑定学生信息***************");
		try {
			String responseString = userManageService.bindStudent(message);
			logger.info(responseString);
			return responseString;
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("获取绑定学生信息接口错误");
			logger.error(responseMessage.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
}

