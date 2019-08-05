package com.qimeng.main;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.StudentDataService;
import com.qimeng.main.service.StudentUpdatePointsService;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentInfoVo;
import com.qimeng.main.vo.UsedPointsVo;

/**
 * 
 * 学生积分更新
 * 
 * @author 陈泽键
 * @date 创建时间：2019年8月2日 下午3:35:37
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
@RequestMapping("/stuinfo")
public class UpdateStudentPointsController {
	private static Logger logger = Logger.getLogger(UpdateStudentPointsController.class);
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisTemplate<String, Serializable> redisCacheTemplate;

	@Autowired
	StudentDataService studentDataService;
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	StudentUpdatePointsService studentUpdatePointsService;

	@RequestMapping("/getstudent")
	public String getStudent(@RequestBody JSONObject message) {

		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});

		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		logger.debug(stuInfoVo.toString());
		try {
			if (applicationManagementService.selectApplicationManagementByAppId(requestMessage.getAppID(),
					(byte) 1) == null) {
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
			String accountTonken = RandomStringUtils.randomAlphanumeric(8);
			while (stringRedisTemplate.hasKey(accountTonken)) {
				accountTonken = RandomStringUtils.randomAlphanumeric(8);
			}
			stringRedisTemplate.opsForValue().set(accountTonken, "", 1L, TimeUnit.MINUTES);
			stuInfoVo.setName(studentData.getName());
			stuInfoVo.setWasteType(0);
			stuInfoVo.setUnit(0);
			stuInfoVo.setPoint(studentData.getTotalPoints()-studentData.getUsedPoints()-studentData.getDeductPoints());
			stuInfoVo.setBind(studentData.getBinding());
			ResponseMessage<StudentInfoVo> responseMessage = new ResponseMessage<StudentInfoVo>();
			responseMessage.setData(stuInfoVo);
			responseMessage.setAccountTonken(accountTonken);
			responseMessage.setSuccessMessage("获取当前用户成功");

			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message) throws Exception {
		RequestMessage<StudentInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentInfoVo>>() {
				});
		StudentInfoVo stuInfoVo = (StudentInfoVo) requestMessage.getData();
		logger.debug(stuInfoVo.toString());
		try {

			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), (byte) 1);
			if (applicationManagement == null||(applicationManagement.getAppType()&(byte)0x1)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

//			String accountTonken = applicationManagementService.decodeMessage(applicationManagement,
//					requestMessage.getAccountTonken());
			/*debug测试*/
			String accountTonken =requestMessage.getAccountTonken();
			
			if (!stringRedisTemplate.hasKey(accountTonken)) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("请求超时");
				System.out.println(responseMessage.toString());
				return JSONObject.toJSONString(responseMessage);
			}
			stringRedisTemplate.delete(accountTonken);

			StudentData studentData = studentDataService.selectStudentDataByCodeAndCard(stuInfoVo.getStuCode(),
					stuInfoVo.getStuCard());
			if (studentData == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("找不到当前学生");
				return JSONObject.toJSONString(responseMessage);
			}
			studentUpdatePointsService.updateStudentPoints(studentData,stuInfoVo.getWasteType(),stuInfoVo.getUnit(),requestMessage.getMachineID());
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("积分上传成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/usedpoints")
	public String usedPoints(@RequestBody JSONObject message) throws Exception {
		
		RequestMessage<UsedPointsVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<UsedPointsVo>>() {
				});
		UsedPointsVo usedPointsVo = (UsedPointsVo) requestMessage.getData();
		logger.debug(usedPointsVo.toString());
		try {

			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), (byte) 1);
			if (applicationManagement == null||(applicationManagement.getAppType()&(byte)0x2)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

//			String accountTonken = applicationManagementService.decodeMessage(applicationManagement,
//					requestMessage.getAccountTonken());
			/*debug测试*/
			String accountTonken =requestMessage.getAccountTonken();
			
			if (!stringRedisTemplate.hasKey(accountTonken)) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("请求超时");
				System.out.println(responseMessage.toString());
				return JSONObject.toJSONString(responseMessage);
			}
			stringRedisTemplate.delete(accountTonken);

			StudentData studentData = studentDataService.selectStudentDataByCodeAndCard(usedPointsVo.getStuCode(),
					usedPointsVo.getStuCard());
			if (studentData == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("找不到当前学生");
				return JSONObject.toJSONString(responseMessage);
			}
			studentUpdatePointsService.usedStudentPoints(studentData,usedPointsVo.getUsedPoints(),usedPointsVo.getMark(),requestMessage.getAppID());
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("使用积分成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/deductpoints")
	public String deductPoints(@RequestBody JSONObject message) throws Exception {
		
		RequestMessage<UsedPointsVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<UsedPointsVo>>() {
				});
		UsedPointsVo usedPointsVo = (UsedPointsVo) requestMessage.getData();
		logger.debug(usedPointsVo.toString());
		try {

			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), (byte) 1);
			if (applicationManagement == null||(applicationManagement.getAppType()&(byte)0x2)==0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

//			String accountTonken = applicationManagementService.decodeMessage(applicationManagement,
//					requestMessage.getAccountTonken());
			/*debug测试*/
			String accountTonken =requestMessage.getAccountTonken();
			
			if (!stringRedisTemplate.hasKey(accountTonken)) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("请求超时");
				System.out.println(responseMessage.toString());
				return JSONObject.toJSONString(responseMessage);
			}
			stringRedisTemplate.delete(accountTonken);

			StudentData studentData = studentDataService.selectStudentDataByCodeAndCard(usedPointsVo.getStuCode(),
					usedPointsVo.getStuCard());
			if (studentData == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("找不到当前学生");
				return JSONObject.toJSONString(responseMessage);
			}
			studentUpdatePointsService.deductStudentPoints(studentData,usedPointsVo.getUsedPoints(),usedPointsVo.getMark(),usedPointsVo.getOperator(),requestMessage.getAppID());
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("扣除积分成功");
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
