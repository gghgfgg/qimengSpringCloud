package com.qimeng.main;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.qimeng.main.vo.OldResponseJson;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentInfoVo;

@Controller
public class mainController {
//
//	@Autowired
//	OldService oldService;
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//	
//	@Autowired
//	RedisTemplate<String, Serializable> redisCacheTemplate;
//	@Autowired
//	ApplicationInfoService applicationInfoService;
//	@Autowired
//	StudentInfoService studentInfoService;
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
//	
//	
//	@RequestMapping("/getstudent")
//	public String getStudent(@RequestBody JSONObject message) {
//
//		RequestMessage<StudentInfoVo> requestMessage=JSON.parseObject(message.toString(), new TypeReference<RequestMessage<StudentInfoVo>>() {});
//		System.out.println(requestMessage.toString());
//		ResponseMessage<StudentInfoVo> responseMessage=new ResponseMessage<StudentInfoVo>();
//		// System.out.println(message.getData().toString());
//		 StudentInfoVo stuInfoVo=(StudentInfoVo)requestMessage.getData();
//		 System.out.println(stuInfoVo.toString());
//		 
////		 String jsonString=oldService.getStudent(stuInfo.getStuCode());
////		 JSONObject jsonObject=JSONObject.parseObject(jsonString);
////		 if(jsonObject.getIntValue("msg")==0)
////		 {
////			 ResponseMessage<StudentInfoVo> responseMessage=new ResponseMessage<StudentInfoVo>();
////			 responseMessage.setData(stuInfo);
////			 responseMessage.setStatusCode(1);
////			 responseMessage.setSuccess(false);
////			 responseMessage.setMessage("找不到当前用户");
////			 System.out.println(responseMessage.toString());
////			 return JSONObject.toJSONString(responseMessage);
////		 }
////		 String accountTonken=RandomStringUtils.randomAlphanumeric(8);
////		 stuInfo.setName(jsonObject.getString("xm"));
////		 stringRedisTemplate.opsForValue().set(accountTonken, "",2,TimeUnit.MINUTES);
////		 ResponseMessage<StudentInfoVo> responseMessage=new ResponseMessage<StudentInfoVo>();
////		 responseMessage.setData(stuInfo);
////		 responseMessage.setAccountTonken(accountTonken);
////		 responseMessage.setStatusCode(1);
////		 responseMessage.setSuccess(true);
////		 responseMessage.setMessage("获取当前用户成功");
////		 System.out.println(responseMessage.toString());
////		 return JSONObject.toJSONString(responseMessage);
//		 
//		 StudentInfo studentInfo=studentInfoService.getStudent(stuInfoVo.getStuCode());
//		 
//		 if(studentInfo==null)
//		 {
//			 responseMessage.setData(stuInfoVo);
//			 responseMessage.setFailedMessage("找不到当前用户");
//			 System.out.println(responseMessage.toString());
//			 return JSONObject.toJSONString(responseMessage);
//		 }
//		 String accountTonken=RandomStringUtils.randomAlphanumeric(8);
//		 stuInfoVo.setName(studentInfo.getName());
//		 redisCacheTemplate.opsForValue().set(accountTonken, studentInfo);
//		 
//		 responseMessage.setData(stuInfoVo);
//		 responseMessage.setAccountTonken(accountTonken);
//		 responseMessage.setSuccessMessage("获取当前用户成功");
//		 System.out.println(responseMessage.toString());
//		 return JSONObject.toJSONString(responseMessage);
//	}
//	@RequestMapping("/uploadpoints")
//	public String upLoadPoints(@RequestBody JSONObject message) throws Exception 
//	{
//		RequestMessage<StudentInfoVo> requestMessage=JSON.parseObject(message.toString(), new TypeReference<RequestMessage<StudentInfoVo>>() {});
//		ResponseMessage<String> responseMessage=new ResponseMessage<String>();
//		
//		ApplicationInfo appInfo=applicationInfoService.getApplicationInfo(requestMessage.getAppID());
//		String accountTonken=applicationInfoService.decodeMessage(appInfo, requestMessage.getAccountTonken());
//		//String accountTonken=requestMessage.getAccountTonken();
//		System.out.println(accountTonken);
//		
//		if(!stringRedisTemplate.hasKey(accountTonken))
//		{
//			responseMessage.setData("");
//			responseMessage.setFailedMessage("请求超时");
//			System.out.println(responseMessage.toString());
//			return JSONObject.toJSONString(responseMessage);
//		}
//		//StudentInfo studentInfo=(StudentInfo)redisCacheTemplate.opsForValue().get(accountTonken);
//		stringRedisTemplate.delete(accountTonken);
//		
//		 StudentInfoVo stuInfo=(StudentInfoVo)requestMessage.getData();
//		
//		 String jsonString=oldService.newuploadIntegral(stuInfo,requestMessage.getMachineID());
//		 System.out.println(jsonString);
//		 
//		 jsonString=oldService.uploadIntegral(stuInfo,requestMessage.getMachineID());
//		 JSONObject jsonObject=JSONObject.parseObject(jsonString);
//		 
//		 if(jsonObject.getIntValue("msg")==1){
//			 responseMessage.setData("");
//			 responseMessage.setSuccessMessage("积分上传成功");
//			 System.out.println(responseMessage.toString());
//		 }else {
//			 responseMessage.setData("");
//			 responseMessage.setFailedMessage("积分上传失败");
//			 System.out.println(responseMessage.toString());
//		}
//		return JSONObject.toJSONString(responseMessage);
//	}
//	@RequestMapping("/updatemachineid")
//	public String updateMachineID(@RequestBody JSONObject message)
//	{
//		RequestMessage<MachineInfo> requestMessage = JSON.parseObject(message.toString(), new TypeReference<RequestMessage<MachineInfo>>() {});
//		ResponseMessage<String> responseMessage=new ResponseMessage<String>();
//		 System.out.println(requestMessage.toString());
//		 MachineInfo machineInfo=(MachineInfo)requestMessage.getData();
//		 System.out.println(machineInfo.toString());
//		 String jsonString=oldService.updateJqm(machineInfo);
//		 applicationInfoService.updateMachineID(machineInfo.getMachineID(), machineInfo.getSerialNumber());
//		 JSONObject jsonObject=JSONObject.parseObject(jsonString);
//		 
//		 if(jsonObject.getBooleanValue("success")){
//			 responseMessage.setData("");
//			 responseMessage.setSuccessMessage("更新成功");
//			 System.out.println(responseMessage.toString());
//		 }else {
//			 responseMessage.setData("");
//			 responseMessage.setFailedMessage("更新失败");
//			 System.out.println(responseMessage.toString());
//		}	 
//		return JSONObject.toJSONString(responseMessage);
//	
//	}
}
