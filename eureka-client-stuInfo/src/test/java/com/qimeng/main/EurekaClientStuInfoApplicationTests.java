package com.qimeng.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.dao.StudentDataDao;
import com.qimeng.main.dao.StudentInformDao;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.DeviceActionService;
import com.qimeng.main.service.DeviceManagementService;
import com.qimeng.main.service.PostalCodeService;
import com.qimeng.main.service.SchoolAutoCountRecycleService;
import com.qimeng.main.service.StudentDataService;
import com.qimeng.main.service.StudentInformService;
import com.qimeng.main.service.StudentRankService;
import com.qimeng.main.service.StudentUpdateService;
import com.qimeng.main.util.EncryptUtil;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.DeviceInformVo;
import com.qimeng.main.vo.StudentVo;

//import com.qimeng.main.Service.ApplicationInfoService;
//import com.qimeng.main.Service.PointsLogService;
//import com.qimeng.main.Service.StudentInfoService;
//import com.qimeng.main.entity.ApplicationInfo;
//import com.qimeng.main.entity.PointsLog;
//import com.qimeng.main.entity.StudentInfo;
//import com.qimeng.main.oldStuService.OldService;
//import com.qimeng.main.vo.OldResponseJson;
//import com.qimeng.main.vo.StudentInfoVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientStuInfoApplicationTests {

	//@Autowired
	//OldService oldService;
	//@Autowired
	//private StringRedisTemplate stringRedisTemplate;
	
	//@Autowired
	//RedisTemplate<String, Serializable> redisCacheTemplate;
	
	//@Autowired
	//ApplicationInfoService applicationInfoService;
	//@Autowired
	//PointsLogService pointsLogService;
	
	//@Autowired
	//StudentInfoService studentInfoService;
	//@Autowired
	//StudentInformService service;
	@Autowired
	StudentDataService service;
	//StudentUpdateService service;
	
	@Autowired
	SchoolAutoCountRecycleService schoolAutoCountRecycleService;
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	PostalCodeService postalCodeService;
	@Autowired
	DeviceManagementService deviceManagementService;
	@Autowired
	JoinDao joinDao;
	@Autowired
	DeviceActionService deviceActionService;
	@Autowired
	StudentRankService studentRankService;
	
	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		
		//OldResponseJson json=oldService.getStudent("5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD");
		//System.out.println(json.toString());
//		ApplicationInfo appInfo=applicationInfoService.getApplicationInfo("7166912116544627");
//		String tempString=applicationInfoService.encodeMessage(appInfo, "ILf4Ys+CXQFk1p52kIFB2g==");
//		System.out.println(tempString);
//		System.out.println(applicationInfoService.decodeMessage(appInfo, "ILf4Ys+CXQFk1p52kIFB2g=="));
//		StudentInfo studentInfo=studentInfoService.getStudent("5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD");
//		System.out.println(studentInfo);
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
//		redisCacheTemplate.opsForValue().set("123",studentInfo);
		
//		StudentInfo studentInfo1=(StudentInfo)redisCacheTemplate.opsForValue().get("123");
//		System.out.println(studentInfo1);
//		List<StudentInform> list=new ArrayList<StudentInform>();
//		StudentInform studentInform=new StudentInform();
//		studentInform.setName("99");
//		studentInform.setStudentCode("G441423201201101050");
//		studentInform.setSchoolCode("123");
//		list.add(studentInform);
//		StudentInform studentInform1=new StudentInform();
//		studentInform1.setName("99");
//		studentInform1.setStudentCode("G441423201201101051");
//		studentInform1.setSchoolCode("123");
//		list.add(studentInform1);
//		StudentInform studentInform2=new StudentInform();
//		studentInform2.setName("99");
//		studentInform2.setStudentCode("G441423201201101052");
//		studentInform2.setSchoolCode("123");
//		list.add(studentInform2);
//		StudentInform studentInform3=new StudentInform();
//		
//		studentInform3.setName("98");
//		studentInform3.setStudentCode("G441423201201101060");
//		studentInform3.setIdentityCard("441423201201101061");
//		studentInform3.setSchoolCode("123");
//		list.add(studentInform3);

//		try {
//			System.out.println(service.insertStudentInformList(list,"12300",(byte) 0));
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.toString());
//		}
		//schoolAutoCountRecycleService.SchoolAutoCountRecycleByYear();
		ApplicationManagement temp=new ApplicationManagement();
		temp.setAppId("7166912116544626");
		temp.setActive((byte) 1);
		temp.setAppName("23");
		//applicationManagementService.insertApplicationManagement(temp);
		
		//deviceManagementService.selectDeviceManagementListByMachineId("123456789",true);
		DeviceInformVo deviceInformVo=new DeviceInformVo();
		deviceInformVo.setSchoolName("45");
		
		deviceInformVo.setPostalCode("");
	//	deviceActionService.DevPageList(1,deviceInformVo);
		//ApplicationManagement temp2=applicationManagementService.selectApplicationManagementByAppId("7166912116544629", (byte) 1);
		//System.out.println(temp2.toString());
		//postalCodeService.selectPostalCodeName("110106");
		//service.selectStudentCountByUpdata("123",new Date());
		//System.out.println(dao.insertStudentDataList(list));
		//System.out.println(dao.insertStudentInform(studentInform3));
		//System.out.println(dao.updateStudentInformByIdentityCardOrStrudentCode(studentInform3));
		//dao.selectStudentInform(studentInform3);
		
//		String tempString="http://m.qimenghb.com/api/stu.php?code=5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD&card=29B2592CC12213D751C1B89BE293232C6049E456&sn=1&state=1";
//		tempString.substring(tempString.indexOf("?")+1);
//		System.out.println(tempString);
//		String []strings = tempString.split("&");
//		String codeString=strings[0].substring(strings[0].indexOf("=")+1);
//		System.out.println(codeString);
//		String cardString=strings[1].substring(strings[1].indexOf("=")+1);
//		System.out.println(cardString);
		
		//studentRankService.addlist();
		//studentRankService.StudentRankList(0,new StudentVo());
		ApplicationManagement applicationManagement = applicationManagementService
				.selectApplicationManagementByAppId("7845345646154645", StaticGlobal.ACTIVE);
		EncryptUtil encryptUtil;
		try {
			encryptUtil = new EncryptUtil(applicationManagement.getDeskey(),applicationManagement.getIvkey());
			String accountTonken=encryptUtil.decode("CUlQiVnY4CrhHuqp0dq9gg==");
			System.out.println(accountTonken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
