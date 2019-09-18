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
import com.qimeng.main.entity.DeviceRecycleOrder;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.DeviceActionService;
import com.qimeng.main.service.DeviceOpenCodeService;
import com.qimeng.main.util.RedisUtil;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.DeviceInformVo;
import com.qimeng.main.vo.DeviceOrderVo;
import com.qimeng.main.vo.DeviceStatusVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月3日 下午10:45:11
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
@RequestMapping("/devinfo")
public class DeviceInformController {

	private static Logger logger = Logger.getLogger(DeviceInformController.class);
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	DeviceActionService deviceActionService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	DeviceOpenCodeService deviceOpenCodeService;
	
	@RequestMapping("/updatedevstatus")
	public String updateDevStatus(@RequestBody JSONObject message) {
		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {
				});

		DeviceStatusVo deviceStatusVo = (DeviceStatusVo) requestMessage.getData();
		if (deviceStatusVo == null || StringUtils.isEmpty(deviceStatusVo.getSerialNumber())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			deviceActionService.updateDevStatus(deviceStatusVo.getSerialNumber(), deviceStatusVo.getStatus(),
					deviceStatusVo.getStatusType());

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("机器状态更新成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/updatemachineid")
	public String updateMachineId(@RequestBody JSONObject message) {
		RequestMessage<DeviceStatusVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceStatusVo>>() {
				});

		DeviceStatusVo deviceStatusVo = (DeviceStatusVo) requestMessage.getData();
		if (deviceStatusVo == null || StringUtils.isEmpty(requestMessage.getMachineID())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(deviceStatusVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			deviceActionService.updateMachineId(requestMessage.getMachineID(), deviceStatusVo.getSerialNumber());

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("机器序列号更新成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/updatedev")
	public String updateDev(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		if (devInformVo == null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			deviceActionService.updateDev(devInformVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新设备信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/getdevlist/{page}")
	public String getDevList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			PageInfo<DeviceInformVo> devPageInfo = deviceActionService.DevPageList(page, devInformVo);
			ResponseMessage<PageInfo<DeviceInformVo>> responseMessage = new ResponseMessage<PageInfo<DeviceInformVo>>();
			responseMessage.setData(devPageInfo);
			responseMessage.setSuccessMessage("获取设备列表信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/getdev")
	public String getDevive(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		if (devInformVo == null || StringUtils.isEmpty(devInformVo.getMachineID())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			DeviceInformVo devInfo = deviceActionService.selectDeviceInformVoByMachineID(devInformVo.getMachineID());
			ResponseMessage<DeviceInformVo> responseMessage = new ResponseMessage<DeviceInformVo>();
			responseMessage.setData(devInfo);
			responseMessage.setSuccessMessage("获取设备信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/savedev")
	public String saveDev(@RequestBody JSONObject message) {
		RequestMessage<DeviceInformVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceInformVo>>() {
				});
		DeviceInformVo devInformVo = (DeviceInformVo) requestMessage.getData();
		if (devInformVo == null || StringUtils.isEmpty(devInformVo.getMachineID())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(devInformVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			deviceActionService.saveDev(devInformVo);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增设备信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/getdevorder")
	public String getDevOrder(@RequestBody JSONObject message) {
		RequestMessage<DeviceOrderVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceOrderVo>>() {
				});
		DeviceOrderVo deviceOrderVo = (DeviceOrderVo) requestMessage.getData();
		if (deviceOrderVo == null || StringUtils.isEmpty(requestMessage.getMachineID())
				|| deviceOrderVo.getOrderType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		if (deviceOrderVo.getOrderType()== StaticGlobal.RECORDER &&deviceOrderVo.getRecycleType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(deviceOrderVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			DeviceRecycleOrder deviceRecycleOrder = null;
			if (deviceOrderVo.getOrderType() == StaticGlobal.RECORDER) {
				deviceRecycleOrder=(DeviceRecycleOrder)redisUtil.get("OrderKey::Recycle::" + requestMessage.getMachineID() + "-"+deviceOrderVo.getRecycleType().toString());
			} else {
				deviceRecycleOrder=(DeviceRecycleOrder)redisUtil.get("OrderKey::Repair::" + requestMessage.getMachineID());
			}
		    if(deviceRecycleOrder==null) {
		    	deviceOpenCodeService.createDevOrder(requestMessage.getMachineID(), deviceOrderVo);
		    	if (deviceOrderVo.getOrderType() == StaticGlobal.RECORDER) {
					deviceRecycleOrder=(DeviceRecycleOrder)redisUtil.get("OrderKey::Recycle::" + requestMessage.getMachineID() + "-"+deviceOrderVo.getRecycleType().toString());
				} else {
					deviceRecycleOrder=(DeviceRecycleOrder)redisUtil.get("OrderKey::Repair::" + requestMessage.getMachineID());
				}
			}
		    deviceOrderVo.setOrder(deviceRecycleOrder.getUuid());
		    deviceOrderVo.setQrOrder("Open$"+deviceRecycleOrder.getUuid());
		    ResponseMessage<DeviceOrderVo> responseMessage = new ResponseMessage<DeviceOrderVo>();
			responseMessage.setData(deviceOrderVo);
			responseMessage.setSuccessMessage("获取设备信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/getopencode")
	public String getOpenCode(@RequestBody JSONObject message) {
		RequestMessage<DeviceOrderVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceOrderVo>>() {
				});
		DeviceOrderVo deviceOrderVo = (DeviceOrderVo) requestMessage.getData();
		logger.debug(deviceOrderVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			if(redisUtil.hasKey("OpenKey")) {
				deviceOrderVo=(DeviceOrderVo)redisUtil.get("OpenKey");
			}
			else {
				deviceOpenCodeService.createStaticOpenCode();
				deviceOrderVo=(DeviceOrderVo)redisUtil.get("OpenKey");
			}
			ResponseMessage<DeviceOrderVo> responseMessage = new ResponseMessage<DeviceOrderVo>();
			responseMessage.setData(deviceOrderVo);
			responseMessage.setSuccessMessage("获取设备开门码成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/uploadorder")
	public String uploadOrder(@RequestBody JSONObject message) {
		RequestMessage<DeviceOrderVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<DeviceOrderVo>>() {
				});
		DeviceOrderVo deviceOrderVo = (DeviceOrderVo) requestMessage.getData();
		if (deviceOrderVo == null || StringUtils.isEmpty(requestMessage.getMachineID())
				|| StringUtils.isEmpty(deviceOrderVo.getOrder())|| deviceOrderVo.getStatus()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(deviceOrderVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			if(deviceOrderVo.getStatus()==StaticGlobal.OPEN) {
				deviceOrderVo=deviceOpenCodeService.checkDevOrder(requestMessage.getMachineID(), deviceOrderVo);
				ResponseMessage<DeviceOrderVo> responseMessage = new ResponseMessage<DeviceOrderVo>();
				responseMessage.setData(deviceOrderVo);
				responseMessage.setSuccessMessage("获取设备开门码成功");
				return JSONObject.toJSONString(responseMessage);
			}
			if(deviceOrderVo.getStatus()==StaticGlobal.CLOSE) {
				if(deviceOpenCodeService.endDevOrder(requestMessage.getMachineID(), deviceOrderVo)==0) {
					return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("找不到设备信息"));
				}
				return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("设备关门信息成功"));
			}
			if(deviceOrderVo.getStatus()==StaticGlobal.UPLOAD) {
				if(deviceOpenCodeService.uploadDevOrder(requestMessage.getMachineID(), deviceOrderVo)==0) {
					return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("找不到设备信息"));
				}
				return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("设备上传重量信息成功"));
			}
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("回收机状态值不对"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
}
