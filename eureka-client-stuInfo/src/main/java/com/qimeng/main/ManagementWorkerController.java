package com.qimeng.main;

import java.util.Date;
import java.util.UUID;

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
import com.qimeng.main.entity.ManagementWorker;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.ManagementWorkerService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月17日 下午9:29:39 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/worker")
public class ManagementWorkerController {

	private static Logger logger = Logger.getLogger(ManagementWorkerController.class);
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	ManagementWorkerService managementWorkerService;
	
	@RequestMapping("/getworkerlist/{page}")
	public String getWorkerList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<ManagementWorker> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<ManagementWorker>>() {
				});
		ManagementWorker managementWorker = (ManagementWorker) requestMessage.getData();
		logger.debug(managementWorker.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			PageInfo<ManagementWorker> managementWorkerPageInfo = managementWorkerService.managementWorkerPageList(page, managementWorker);
			
			ResponseMessage<PageInfo<ManagementWorker>> responseMessage = new ResponseMessage<PageInfo<ManagementWorker>>();
			responseMessage.setData(managementWorkerPageInfo);
			responseMessage.setSuccessMessage("获取回收机工作单位列表成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/getworker")
	public String getWorker(@RequestBody JSONObject message) {
		RequestMessage<ManagementWorker> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<ManagementWorker>>() {
				});
		ManagementWorker managementWorker = (ManagementWorker) requestMessage.getData();
		if(managementWorker==null||StringUtils.isEmpty(managementWorker.getUuid())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(managementWorker.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			ManagementWorker worker = managementWorkerService.selectManagementWorker(managementWorker.getUuid(), managementWorker.getActive());
			
			ResponseMessage<ManagementWorker> responseMessage = new ResponseMessage<ManagementWorker>();
			responseMessage.setData(worker);
			responseMessage.setSuccessMessage("获取回收机工作单位信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/saveworker")
	public String saveWorker(@RequestBody JSONObject message) {
		RequestMessage<ManagementWorker> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<ManagementWorker>>() {
				});
		ManagementWorker managementWorker = (ManagementWorker) requestMessage.getData();
		if(managementWorker==null||StringUtils.isEmpty(managementWorker.getName())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(managementWorker.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			Date date=new Date();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			managementWorker.setUuid(uuid);
			managementWorker.setActive((byte)1);
			managementWorker.setType(managementWorker.getType()==null?0:managementWorker.getType());
			managementWorker.setActivityCount(0);
			managementWorker.setCreateTime(date);
			managementWorker.setLastTime(date);
		    managementWorkerService.insertManagementWorker(managementWorker);
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增回收机工作单位信息成功"));

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updateworker")
	public String updateWorker(@RequestBody JSONObject message) {
		RequestMessage<ManagementWorker> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<ManagementWorker>>() {
				});
		ManagementWorker managementWorker = (ManagementWorker) requestMessage.getData();
		if(managementWorker==null||StringUtils.isEmpty(managementWorker.getUuid())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(managementWorker.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			Date date=new Date();
			managementWorker.setLastTime(date);
		    managementWorkerService.updateManagementWorker(managementWorker);
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新回收机工作单位信息成功"));

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
}

