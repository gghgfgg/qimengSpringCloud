package com.qimeng.main;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.DeviceCurrentState;
import com.qimeng.main.entity.DeviceManagement;
import com.qimeng.main.entity.DeviceState;
import com.qimeng.main.entity.DeviceStateLog;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.DeviceCurrentStateService;
import com.qimeng.main.service.DeviceManagementService;
import com.qimeng.main.service.DeviceStateLogService;
import com.qimeng.main.service.DeviceStateService;
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
public class UpdateDeviceInform {
	
	private static Logger logger = Logger.getLogger(UpdateDeviceInform.class);
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	DeviceStateLogService deviceStateLogService;
	@Autowired
	DeviceStateService deviceStateService;
	@Autowired
	DeviceCurrentStateService deviceCurrentStateService;
	@Autowired
	DeviceManagementService deviceManagementService;
	
	@RequestMapping("/updatedevstatus")
	public String updateDevStatus(@RequestBody JSONObject message) {
		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {});
		
		DeviceStatusVo deviceStatusVo = (DeviceStatusVo) requestMessage.getData();
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), (byte) 1);
			if (applicationManagement == null||(applicationManagement.getAppType()&(byte)0x4)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			Date date=new Date();
			DeviceState deviceState=deviceStateService.selectDeviceState(deviceStatusVo.getStatusType(),deviceStatusVo.getStatus());
			DeviceStateLog deviceStateLog=new DeviceStateLog();
			deviceStateLog.setSerialNumber(deviceStatusVo.getSerialNumber());
			deviceStateLog.setStatus(deviceStatusVo.getStatus());
			deviceStateLog.setStatusType(deviceStatusVo.getStatusType());
			deviceStateLog.setMark(deviceState.getMark());
			deviceStateLog.setCreateTime(date);
			deviceStateLogService.insertDeviceStateLog(deviceStateLog);
			
			DeviceCurrentState deviceCurrentState=new DeviceCurrentState();
			deviceCurrentState.setSerialNumber(deviceStatusVo.getSerialNumber());
			deviceCurrentState.setStatus(deviceStatusVo.getStatus());
			deviceCurrentState.setStatusType(deviceStatusVo.getStatusType());
			deviceCurrentState.setCreateTime(date);
			deviceCurrentState.setUpdateTime(date);
			deviceCurrentStateService.insertDeviceStateLog(deviceCurrentState);
			
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
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), (byte) 1);
			if (applicationManagement == null||(applicationManagement.getAppType()&(byte)0x4)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			Date date=new Date();
			DeviceManagement deviceManagement=new DeviceManagement();
			deviceManagement.setMachineId(requestMessage.getMachineID());
			deviceManagement.setSerialNumber(deviceStatusVo.getSerialNumber());
			deviceManagement.setUpdateTime(date);
			if(deviceManagementService.updateDeviceManagement(deviceManagement)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setSuccessMessage("机器序列号更新失败");
				return JSONObject.toJSONString(responseMessage);
			}
			
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
}

