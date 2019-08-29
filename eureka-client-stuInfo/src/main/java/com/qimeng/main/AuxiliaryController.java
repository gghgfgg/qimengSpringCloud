package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.DeviceState;
import com.qimeng.main.entity.RecycleType;
import com.qimeng.main.entity.SchoolContactsType;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.AuxiliaryService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.AuxiliaryVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月20日 上午9:20:28 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/auxiliary")
public class AuxiliaryController {
private static Logger logger = Logger.getLogger(AuxiliaryController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	AuxiliaryService auxiliaryService;
	
	@RequestMapping("/devstatelist/{page}")
	public String devStateList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			PageInfo<DeviceState> auxiliaryPageInfo=auxiliaryService.auxiliaryDeviceStatePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<DeviceState>> responseMessage = new ResponseMessage<PageInfo<DeviceState>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/recycletypelist/{page}")
	public String recycleTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			PageInfo<RecycleType> auxiliaryPageInfo=auxiliaryService.auxiliaryRecycleTypePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<RecycleType>> responseMessage = new ResponseMessage<PageInfo<RecycleType>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/contactstypelist/{page}")
	public String contactsTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			PageInfo<SchoolContactsType> auxiliaryPageInfo=auxiliaryService.auxiliaryContactsTypePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<SchoolContactsType>> responseMessage = new ResponseMessage<PageInfo<SchoolContactsType>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	
	@RequestMapping("/devstate")
	public String devState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryDeviceState(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/recycletype")
	public String recycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryRecycleType(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/contactstype")
	public String contactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryContactsType(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	
	@RequestMapping("/savedevstate")
	public String saveDevState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getStatus()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.saveDeviceState(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("新增设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/saverecycletype")
	public String saveRecycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getFactor()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.saveRecycleType(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("新增回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/savecontactstype")
	public String saveContactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getWeight()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.saveContactsType(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("新增联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	
	@RequestMapping("/updatedevstate")
	public String updateDevState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getStatus()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.updateDevState(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/updaterecycletype")
	public String updateRecycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getFactor()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.updateRecycleType(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/updatecontactstype")
	public String updateContactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getWeight()==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null||(applicationManagement.getAppType()&StaticGlobal.UPDATE)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			auxiliaryService.updateContactsType(auxiliaryVo);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新联系人信息成功");
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

