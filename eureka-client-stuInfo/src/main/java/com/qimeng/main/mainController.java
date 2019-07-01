package com.qimeng.main;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.Service.ApplicationInfoService;
import com.qimeng.main.entity.ApplicationInfo;
import com.qimeng.main.oldStuService.OldService;
import com.qimeng.main.vo.MachineInfo;
import com.qimeng.main.vo.OldResponseJson;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentInfo;

@RestController
public class mainController {

	@Autowired
	OldService oldService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	ApplicationInfoService applicationInfoService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/getstudent")
	public String getStudent(@RequestBody JSONObject message) {
		//("appID") String appID,@RequestParam("machineNumber") String machineNumber,@RequestParam("data") StudentInfo data
//		 System.out.println(appID);
//		 System.out.println(machineNumber);
//		 System.out.println(data.toString());
		// RequestMessage message=JSONObject.parseObject(params,RequestMessage.class);
		RequestMessage<StudentInfo> requestMessage=new RequestMessage<StudentInfo>();
		requestMessage = JSON.parseObject(message.toString(), new TypeReference<RequestMessage<StudentInfo>>() {
        });
		
		 System.out.println(message.toString());
		 System.out.println(requestMessage.toString());
		// System.out.println(message.getData().toString());
		 StudentInfo stuInfo=(StudentInfo)requestMessage.getData();
		 System.out.println(stuInfo.toString());
		 
		 OldResponseJson oldJsom=oldService.getStudent(stuInfo.getStuCode());
		 if(oldJsom.getMsg()==0)
		 {
			 ResponseMessage<String> responseMessage=new ResponseMessage<String>();
			 responseMessage.setData("");
			 responseMessage.setStatusCode(1);
			 responseMessage.setSuccess(false);
			 responseMessage.setMessage("找不到当前用户");
			 System.out.println(responseMessage.toString());
			 return JSONObject.toJSONString(responseMessage);
		 }
		 String accountTonken=RandomStringUtils.randomAlphanumeric(8);
		 stuInfo.setName(oldJsom.getXm());
		 stringRedisTemplate.opsForValue().set(accountTonken, "",2,TimeUnit.MINUTES);
		 ResponseMessage<StudentInfo> responseMessage=new ResponseMessage<StudentInfo>();
		 responseMessage.setData(stuInfo);
		 responseMessage.setAccountTonken(accountTonken);
		 responseMessage.setStatusCode(1);
		 responseMessage.setSuccess(true);
		 responseMessage.setMessage("获取当前用户成功");
		 System.out.println(responseMessage.toString());
		 return JSONObject.toJSONString(responseMessage);
	}
	@RequestMapping("/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message) throws Exception 
	{
		RequestMessage<StudentInfo> requestMessage=new RequestMessage<StudentInfo>();
		requestMessage = JSON.parseObject(message.toString(), new TypeReference<RequestMessage<StudentInfo>>() {
        });
		
//		ApplicationInfo appInfo=applicationInfoService.getApplicationInfo(requestMessage.getAppID());
//		String accountTonken=applicationInfoService.decodeMessage(appInfo, requestMessage.getAccountTonken());
//		System.out.println(accountTonken);
//		if(stringRedisTemplate.hasKey(accountTonken))
//		{
//			stringRedisTemplate.delete(accountTonken);
//			 StudentInfo stuInfo=(StudentInfo)requestMessage.getData();
//			 System.out.println(stuInfo.toString());
//			 OldResponseJson oldJsom=oldService.uploadIntegral(stuInfo,requestMessage.getMachineNumber());
//			return 
//		}
		 StudentInfo stuInfo=(StudentInfo)requestMessage.getData();
		 System.out.println(stuInfo.toString());
		 return oldService.uploadIntegral(stuInfo,requestMessage.getMachineID());
//		ResponseMessage<String> responseMessage=new ResponseMessage<String>();
//		responseMessage.setData("");
//		responseMessage.setStatusCode(1);
//		responseMessage.setSuccess(false);
//		responseMessage.setMessage("请求超时");
//		System.out.println(responseMessage.toString());
//		return JSONObject.toJSONString(responseMessage);
		 
	}
	@RequestMapping("/updatemachineid")
	public String updateMachineID(@RequestBody JSONObject message)
	{
		RequestMessage<MachineInfo> requestMessage=new RequestMessage<MachineInfo>();
		requestMessage = JSON.parseObject(message.toString(), new TypeReference<RequestMessage<MachineInfo>>() {
        });
		 System.out.println(message.toString());
		 System.out.println(requestMessage.toString());
		// System.out.println(message.getData().toString());
		 MachineInfo machineInfo=(MachineInfo)requestMessage.getData();
		 System.out.println(machineInfo.toString());
		 
		 ResponseMessage<Object> responseMessage=new ResponseMessage<Object>();
		 responseMessage.setData(null);
		 responseMessage.setAccountTonken("");
		 responseMessage.setStatusCode(1);
		 responseMessage.setSuccess(true);
		 responseMessage.setMessage("更新成功");
		 System.out.println(responseMessage.toString());
		 return JSONObject.toJSONString(responseMessage);
	
	}
}
