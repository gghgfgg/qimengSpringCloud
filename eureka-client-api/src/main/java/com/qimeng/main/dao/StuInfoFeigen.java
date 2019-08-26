package com.qimeng.main.dao;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@FeignClient("eureka-client-stuInfo")
public interface StuInfoFeigen {
	@RequestMapping("/index")
	public String index();
	
	//学生积分相关
	@RequestMapping("/stuinfo/getstudent")
	public String getStudent(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/usedpoints")
	public String usedPoints(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/deductpoints")
	public String deductPoints(@RequestBody JSONObject message);
	
	
	//回收机设备相关
	@RequestMapping("/devinfo/updatemachineid")
	public String updateMachineID(@RequestBody JSONObject message);
	
	@RequestMapping("/devinfo/updatedevstatus")
	public String updateDevStatus(@RequestBody JSONObject message);
	
	@RequestMapping("/devinfo/updatedev")
	public String updateDev(@RequestBody JSONObject message);
	
	@RequestMapping("/devinfo/getdevlist/{page}")
	public String getDevList(@PathVariable("page") Integer page,@RequestBody JSONObject message);
	
	@RequestMapping("/devinfo/getdev")
	public String getDevive(@RequestBody JSONObject message);
	
	@RequestMapping("/devinfo/savedev")
	public String saveDev(@RequestBody JSONObject message);
	
	
	//学生信息
	@RequestMapping("/stuinfo/getstudatalist/{page}")
	public String getStudentDataList(@PathVariable("page") Integer page, @RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/getstuinfo")
	public String getStudentInfo(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/uploadstulist")
	public String uploadStudentList(String message,MultipartFile file);
	
	@RequestMapping("/stuinfo/savestudata")
	public String saveStudentData(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/updatestudata")
	public String updateStudentData(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/updatestuactive")
	public String updateStudentActive(@RequestBody JSONObject message);
	
	@RequestMapping("/stuinfo/exportstudatalist")
	public String exportStudentDataList(@RequestBody JSONObject message,HttpServletResponse response);
	
	
	//学校信息
	@RequestMapping("/schoolinfo/getschoollist/{page}")
	public String getSchoolList(@PathVariable("page") Integer page, @RequestBody JSONObject message);
	
	@RequestMapping("/schoolinfo/getschoolinfo")
	public String getSchoolinfo(@RequestBody JSONObject message);
	
	@RequestMapping("/schoolinfo/saveschool")
	public String saveSchoolinfo(@RequestBody JSONObject message);
	
	@RequestMapping("/schoolinfo/updateschool")
	public String updateSchool(@RequestBody JSONObject message);
	
	
	//辅助类
	@RequestMapping("/auxiliary/devstatelist/{page}")
	public String devStateList(@PathVariable("page") Integer page, @RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/recycletypelist/{page}")
	public String recycleTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/contactstypelist/{page}")
	public String contactsTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message);
	
	
	@RequestMapping("/auxiliary/devstate")
	public String devState(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/recycletype")
	public String recycleType(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/contactstype")
	public String contactsType(@RequestBody JSONObject message);
	
	
	@RequestMapping("/auxiliary/savedevstate")
	public String saveDevState(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/saverecycletype")
	public String saveRecycleType(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/savecontactstype")
	public String saveContactsType(@RequestBody JSONObject message);
	
	
	@RequestMapping("/auxiliary/updatedevstate")
	public String updateDevState(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/updaterecycletype")
	public String updateRecycleType(@RequestBody JSONObject message);
	
	@RequestMapping("/auxiliary/updatecontactstype")
	public String updateContactsType(@RequestBody JSONObject message);
	
	
	//app管理
	@RequestMapping("/appinfo/getapplist/{page}")
	public String getAppInformList(@PathVariable("page") Integer page,@RequestBody JSONObject message);
	
	@RequestMapping("/appinfo/getappinfo")
	public String getAppInform(@RequestBody JSONObject message);
	
	@RequestMapping("/appinfo/updateappinfo")
	public String updateAppInform(@RequestBody JSONObject message);
	
	@RequestMapping("/appinfo/saveappinfo")
	public String saveAppInform(@RequestBody JSONObject message);
}
