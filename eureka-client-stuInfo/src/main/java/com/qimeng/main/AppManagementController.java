package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.AppInformVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月8日 下午1:42:41 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/appinfo")
public class AppManagementController {
	
	private static Logger logger = Logger.getLogger(AppManagementController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	
	@RequestMapping("/getapplist/{page}")
	public String getAppInformList(@PathVariable("page") Integer page,@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			PageInfo<ApplicationManagement> appPageInfo=applicationManagementService.selectAppPageList(page,appInformVo.getAppName(),appInformVo.getActive(),appInformVo.getAppType());
			ResponseMessage<PageInfo<ApplicationManagement>> responseMessage = new ResponseMessage<PageInfo<ApplicationManagement>>();
			responseMessage.setData(appPageInfo);
			responseMessage.setSuccessMessage("获取app信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	@RequestMapping("/getappinfo")
	public String getAppInformList(@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		if(appInformVo==null||StringUtils.isEmpty(appInformVo.getAppId())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			ApplicationManagement appInfo=applicationManagementService.selectApplicationManagementByAppId(appInformVo.getAppId(), null);
			ResponseMessage<ApplicationManagement> responseMessage = new ResponseMessage<ApplicationManagement>();
			responseMessage.setData(appInfo);
			responseMessage.setSuccessMessage("获取app信息成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/updateappinfo")
	public String updateAppInform(@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		if(appInformVo==null||StringUtils.isEmpty(appInformVo.getAppId())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null ||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			applicationManagementService.updateApplicationManagement(appInformVo.getAppId(),appInformVo.getAppName(),
					appInformVo.getActive(),appInformVo.getAppType(),appInformVo.getDeskey(),appInformVo.getIvkey());
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("修改app信息成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/saveappinfo")
	public String saveAppInform(@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		if(appInformVo==null||StringUtils.isEmpty(appInformVo.getAppId())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null ||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			applicationManagementService.insertApplicationManagement(appInformVo.getAppId(),appInformVo.getAppName(),
					appInformVo.getActive(),appInformVo.getAppType(),appInformVo.getDeskey(),appInformVo.getIvkey());
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("新增app信息成功");
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

