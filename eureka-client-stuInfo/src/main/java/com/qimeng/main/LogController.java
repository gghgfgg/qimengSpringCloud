package com.qimeng.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.DeviceRecycleLog;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.DeviceRecycleLogService;
import com.qimeng.main.service.StudentDataService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.RecycleLogVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentInfoVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月1日 下午2:57:46 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/log")
public class LogController {
private static Logger logger = Logger.getLogger(LogController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	StudentDataService studentDataService;
	@Autowired
	DeviceRecycleLogService deviceRecycleLogService;
	
	@RequestMapping("/getrecyclelog")
	public String getAppInformList(@RequestBody JSONObject message) {
		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});

		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		if(stuInfoVo==null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(stuInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			StudentData studentData = studentDataService.selectStudentDataByCodeAndCard(stuInfoVo.getStuCode(),
					stuInfoVo.getStuCard());
			if (studentData == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("找不到当前学生");
				return JSONObject.toJSONString(responseMessage);
			}
			if (studentData.getActive()==StaticGlobal.CLOSED) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("当前账号已被封停，请规范使用");
				return JSONObject.toJSONString(responseMessage);
			}
			PageHelper.startPage(1, 7);
	        List<DeviceRecycleLog> news = deviceRecycleLogService.selectDeviceRecycleLogListByUuid(studentData.getUuid());
	        PageInfo<DeviceRecycleLog> logPageInfo = new PageInfo<>(news);
	        
	        List<RecycleLogVo> list=new ArrayList<RecycleLogVo>();
	        for (DeviceRecycleLog item : logPageInfo.getList()) {
	        	RecycleLogVo vo=new RecycleLogVo();
	        	vo.setPoints(item.getPoints());
	        	vo.setRemainder(item.getRemainder());
	        	vo.setCount(item.getCount());
	        	vo.setCreateTime(item.getCreateTime());
	        	list.add(vo);
			}       
			ResponseMessage<List<RecycleLogVo>> responseMessage = new ResponseMessage<List<RecycleLogVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取日志信息成功");
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

