package com.qimeng.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.oldStuService.OldService;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.StudentInfo;

@RestController
public class mainController {

	@Autowired
	OldService oldService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/getStudent")
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
		 
		return oldService.getStudent(stuInfo.getStuCode()).toString();
	}
}
