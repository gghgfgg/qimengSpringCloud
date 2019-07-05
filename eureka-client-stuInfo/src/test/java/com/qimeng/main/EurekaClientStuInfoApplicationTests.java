package com.qimeng.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.qimeng.main.Service.ApplicationInfoService;
import com.qimeng.main.Service.PointsLogService;
import com.qimeng.main.Service.StudentInfoService;
import com.qimeng.main.entity.ApplicationInfo;
import com.qimeng.main.entity.PointsLog;
import com.qimeng.main.entity.StudentInfo;
import com.qimeng.main.oldStuService.OldService;
import com.qimeng.main.vo.OldResponseJson;
import com.qimeng.main.vo.StudentInfoVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientStuInfoApplicationTests {

	@Autowired
	OldService oldService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate<String, Serializable> redisCacheTemplate;
	
	@Autowired
	ApplicationInfoService applicationInfoService;
	@Autowired
	PointsLogService pointsLogService;
	
	@Autowired
	StudentInfoService studentInfoService;
	
	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		
		//OldResponseJson json=oldService.getStudent("5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD");
		//System.out.println(json.toString());
//		ApplicationInfo appInfo=applicationInfoService.getApplicationInfo("7166912116544627");
//		String tempString=applicationInfoService.encodeMessage(appInfo, "ILf4Ys+CXQFk1p52kIFB2g==");
//		System.out.println(tempString);
//		System.out.println(applicationInfoService.decodeMessage(appInfo, "ILf4Ys+CXQFk1p52kIFB2g=="));
		StudentInfo studentInfo=studentInfoService.getStudent("5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD");
		System.out.println(studentInfo);
//		PointsLog pointsLog=new PointsLog();
//		pointsLog.setMachineID("123456789");
//		pointsLog.setPoints(10);
//		pointsLog.setStuId(studentInfo.getId());
//		pointsLog.setUnit(700);
//		pointsLog.setUploadTime(new Date());
//		pointsLog.setWasteType(2);
//		pointsLogService.insertPointsLog(pointsLog);
//		System.out.println(pointsLog);
//		studentInfo.setStartTime(new Date());
//		System.out.println(studentInfoService.UpdataStudentInfo(studentInfo));
		redisCacheTemplate.opsForValue().set("123",studentInfo);
		
		StudentInfo studentInfo1=(StudentInfo)redisCacheTemplate.opsForValue().get("123");
		System.out.println(studentInfo1);
	}

}
