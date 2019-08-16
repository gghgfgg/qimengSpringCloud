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
import com.qimeng.main.service.DeviceActionService;

import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.DeviceInformVo;
import com.qimeng.main.vo.DeviceStatusVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午10:45:11 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/devinfo")
public class DeviceInformController {
	
	private static Logger logger = Logger.getLogger(DeviceInformController.class);
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	DeviceActionService deviceActionService;
	
	
	@RequestMapping("/updatedevstatus")
	public String updateDevStatus(@RequestBody JSONObject message) {
		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {});
		
		DeviceStatusVo deviceStatusVo = (DeviceStatusVo) requestMessage.getData();
		if(deviceStatusVo==null||StringUtils.isEmpty(deviceStatusVo.getSerialNumber())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			deviceActionService.updateDevStatus(deviceStatusVo.getSerialNumber(),deviceStatusVo.getStatus(),deviceStatusVo.getStatusType());
			
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("机器状态更新成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	@RequestMapping("/updatemachineid")
	public String updateMachineId(@RequestBody JSONObject message) {
		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {});
		
		DeviceStatusVo deviceStatusVo = (DeviceStatusVo) requestMessage.getData();
		if(deviceStatusVo==null||StringUtils.isEmpty(requestMessage.getMachineID())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			deviceActionService.updateMachineId(requestMessage.getMachineID(),deviceStatusVo.getSerialNumber());
		
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("机器序列号更新成功");
			return JSONObject.toJSONString(responseMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	
	@RequestMapping("/updatedev")
	public String updateDev(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			deviceActionService.updateDev(devInformVo);
			
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新设备信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/getdevlist/{page}")
	public String getDevList(@PathVariable("page") Integer page,@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			PageInfo<DeviceInformVo> devPageInfo=deviceActionService.DevPageList(page,devInformVo);
			ResponseMessage<PageInfo<DeviceInformVo>> responseMessage = new ResponseMessage<PageInfo<DeviceInformVo>>();
			responseMessage.setData(devPageInfo);
			responseMessage.setSuccessMessage("获取设备列表信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/getdev")
	public String getDevive(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		if(devInformVo==null||StringUtils.isEmpty(devInformVo.getMachineID())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			DeviceInformVo devInfo=deviceActionService.selectDeviceInformVoByMachineID(devInformVo.getMachineID());
			ResponseMessage<DeviceInformVo> responseMessage = new ResponseMessage<DeviceInformVo>();
			responseMessage.setData(devInfo);
			responseMessage.setSuccessMessage("获取设备信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/savedev")
	public String saveDev(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			deviceActionService.saveDev(devInformVo);
			
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("新增设备信息成功");
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

