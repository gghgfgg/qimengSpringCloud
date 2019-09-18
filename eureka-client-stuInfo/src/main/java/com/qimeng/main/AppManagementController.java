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
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			int appType=0;
			if(appInformVo.getAdd()!=null&&appInformVo.getAdd()) {
				appType=appType|StaticGlobal.ADD;
			}
			if(appInformVo.getSub()!=null&&appInformVo.getSub()) {
				appType=appType|StaticGlobal.SUB;
			}
			if(appInformVo.getUpdate()!=null&&appInformVo.getUpdate()) {
				appType=appType|StaticGlobal.UPDATE;
			}
			PageInfo<ApplicationManagement> appPageInfo=applicationManagementService.selectAppPageList(page,appInformVo.getAppName(),appInformVo.getActive(),(byte)appType);
			ResponseMessage<PageInfo<ApplicationManagement>> responseMessage = new ResponseMessage<PageInfo<ApplicationManagement>>();
			responseMessage.setData(appPageInfo);
			responseMessage.setSuccessMessage("获取app信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	@RequestMapping("/getappinfo")
	public String getAppInform(@RequestBody JSONObject message) {
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
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			ApplicationManagement appInfo=applicationManagementService.selectApplicationManagementByAppId(appInformVo.getAppId(), null);
			
			appInformVo.setAppId(appInfo.getAppId());
			appInformVo.setAppName(appInfo.getAppName());
			appInformVo.setDeskey(appInfo.getDeskey());
			appInformVo.setIvkey(appInfo.getIvkey());
			appInformVo.setAdd((appInfo.getAppType()&StaticGlobal.ADD)==StaticGlobal.ADD?true:false);
			appInformVo.setUpdate((appInfo.getAppType()&StaticGlobal.UPDATE)==StaticGlobal.UPDATE?true:false);
			appInformVo.setSub((appInfo.getAppType()&StaticGlobal.SUB)==StaticGlobal.SUB?true:false);
			appInformVo.setRead(true);
			ResponseMessage<AppInformVo> responseMessage = new ResponseMessage<AppInformVo>();
			responseMessage.setData(appInformVo);
			responseMessage.setSuccessMessage("获取app信息成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updateappinfo")
	public String updateAppInform(@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		if(appInformVo==null||StringUtils.isEmpty(appInformVo.getAppId())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			int appType=0;
			if(appInformVo.getAdd()) {
				appType=appType|StaticGlobal.ADD;
			}
			if(appInformVo.getSub()) {
				appType=appType|StaticGlobal.SUB;
			}
			if(appInformVo.getUpdate()) {
				appType=appType|StaticGlobal.UPDATE;
			}
			applicationManagementService.updateApplicationManagement(appInformVo.getAppId(),appInformVo.getAppName(),
					appInformVo.getActive(),(byte)appType,appInformVo.getDeskey(),appInformVo.getIvkey());
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("修改app信息成功"));
			
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/saveappinfo")
	public String saveAppInform(@RequestBody JSONObject message) {
		RequestMessage<AppInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AppInformVo>>() {
				});
		AppInformVo appInformVo = (AppInformVo) requestMessage.getData();
		if(appInformVo==null||StringUtils.isEmpty(appInformVo.getAppId())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(appInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			int appType=0;
			if(appInformVo.getAdd()) {
				appType=appType|StaticGlobal.ADD;
			}
			if(appInformVo.getSub()) {
				appType=appType|StaticGlobal.SUB;
			}
			if(appInformVo.getUpdate()) {
				appType=appType|StaticGlobal.UPDATE;
			}
			applicationManagementService.insertApplicationManagement(appInformVo.getAppId(),appInformVo.getAppName(),
					appInformVo.getActive(),(byte)appType,appInformVo.getDeskey(),appInformVo.getIvkey());
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增app信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
}

