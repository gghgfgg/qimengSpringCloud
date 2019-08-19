package com.qimeng.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.StudentService;
import com.qimeng.main.service.StudentUpdateService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.util.UpLoadFile;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentVo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月15日 上午9:04:28
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
@RequestMapping("/stuinfo")
public class StudentInformController {
	private static Logger logger = Logger.getLogger(StudentInformController.class);

	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	StudentService studentService;
	@Autowired
	UpLoadFile upLoadFile;
	@Autowired
	StudentUpdateService studentUpdateService;
	@RequestMapping("/getstudatalist/{page}")
	public String getstudentDataList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			PageInfo<StudentVo> studentPageInfo = studentService.StudentPageList(page, studentVo);
			ResponseMessage<PageInfo<StudentVo>> responseMessage = new ResponseMessage<PageInfo<StudentVo>>();
			responseMessage.setData(studentPageInfo);
			responseMessage.setSuccessMessage("获取学生列表信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/getstuinfo")
	public String getstudentInfo(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			StudentVo studentInfo = studentService.selectStudent(studentVo);
			ResponseMessage<StudentVo> responseMessage = new ResponseMessage<StudentVo>();
			responseMessage.setData(studentInfo);
			responseMessage.setSuccessMessage("获取学生信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/uploadstulist")
	public String uploadstudentList(String message,MultipartFile file) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message,
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		if(studentVo==null||StringUtils.isEmpty(studentVo.getSchoolCode())) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("参数不足");
			return JSONObject.toJSONString(responseMessage);
		}
		if(file == null || file.getSize()==0) {
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage("无效文件");
			return JSONObject.toJSONString(responseMessage);
		}
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			
			String fileName = file.getOriginalFilename(); //文件名
			File uploadFlie=upLoadFile.getUploadFile(fileName,requestMessage.getAppID(),requestMessage.getOperator());
			file.transferTo(uploadFlie);

			List<StudentInform> list =upLoadFile.uploadFileToList(uploadFlie);
			int updatecount=studentUpdateService.insertStudentInformList(list, studentVo.getSchoolCode(), studentVo.getType());
			String reString="读取文件学生数:"+list.size()+",更新学生数:"+updatecount;
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData(reString);
			responseMessage.setSuccessMessage("更新学生信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/savestudata")
	public String savestudentData(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			studentService.savestudentData(studentVo);

			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新学生信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/updatestudata")
	public String updatestudentData(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			studentService.updatestudentData(studentVo);

			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新学生信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/updatestuactive")
	public String updateStudentActive(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}
			studentService.updataStudentActive(studentVo);

			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新学生信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	@RequestMapping("/exportstudatalist")
	public String exportstudentDataList(@RequestBody JSONObject message,HttpServletResponse response) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			List<StudentVo> studentList = studentService.StudentList(studentVo);
			File upload = new File("/export/");
			if (!upload.exists()) {
				upload.mkdirs();
			}
			String path = upload.getAbsolutePath(); // 本地路径
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
			String time = simpleDateFormat.format(System.currentTimeMillis());
			String url = "/" + time + "学生信息表.xls";
			// 存储地址
			String destFileName = path + url;
			File destFile = new File(destFileName);
			destFile.getParentFile().mkdirs();
			upLoadFile.exporFile(studentList,"学生信息表",destFile);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData(url);
			responseMessage.setSuccessMessage("导出学生列表信息成功");
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
