package com.qimeng.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.GlobalDateService;
import com.qimeng.main.service.StudentDataService;
import com.qimeng.main.service.StudentService;
import com.qimeng.main.service.StudentUpdateService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.util.UpLoadFile;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentBindVo;
import com.qimeng.main.vo.StudentInfoVo;
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
	@Autowired
	StudentDataService studentDataService;
	@Autowired
	GlobalDateService globalDateService;

	@RequestMapping("/getstudatalist/{page}")
	public String getStudentDataList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
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
	public String getStudentInfo(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		if (studentVo == null || StringUtils.isEmpty(studentVo.getUuid())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
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
	public String uploadStudentList(String message, MultipartFile file) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message,
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		if (studentVo == null || StringUtils.isEmpty(studentVo.getSchoolCode())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		if (file == null || file.getSize() == 0) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("无效文件"));
		}
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			String fileName = file.getOriginalFilename(); // 文件名
			File uploadFlie = upLoadFile.getUploadFile(fileName, requestMessage.getAppID(),
					requestMessage.getOperator());
			file.transferTo(uploadFlie);

			List<StudentInform> list = upLoadFile.uploadFileToList(uploadFlie);
			int updatecount = studentUpdateService.insertStudentInformList(list, studentVo.getSchoolCode(),
					studentVo.getType());
			String reString = "读取文件学生数:" + list.size() + ",更新学生数:" + updatecount;
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData(reString);
			responseMessage.setSuccessMessage("更新学生信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/savestudata")
	public String saveStudentData(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		if (studentVo == null || StringUtils.isEmpty(studentVo.getSchoolId())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			studentService.savestudentData(studentVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学生信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/updatestudata")
	public String updateStudentData(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			studentService.updatestudentData(studentVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学生信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
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
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			studentService.updataStudentActive(studentVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学生信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/exportstudatalist")
	public String exportStudentDataList(@RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
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
			upLoadFile.exporFile(studentList, "学生信息表", destFile);
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData(url);
			responseMessage.setSuccessMessage("导出学生列表信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/stubindbycode")
	public String stuBindByCode(@RequestBody JSONObject message) {
		RequestMessage<StudentBindVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentBindVo>>() {
				});
		StudentBindVo studentBindVo = (StudentBindVo) requestMessage.getData();
		if(studentBindVo==null||StringUtils.isEmpty(studentBindVo.getName())
				||StringUtils.isEmpty(studentBindVo.getStuCard())
				||StringUtils.isEmpty(studentBindVo.getStuCode())
				||studentBindVo.getBind()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(studentBindVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			StudentData studentData=studentDataService.selectStudentDataByCodeAndCard(studentBindVo.getStuCode(), studentBindVo.getStuCard());
			if(studentData==null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("查找不到当前学生"));
			}
			
			if(studentBindVo.getBind()==StaticGlobal.NOBIND) {
				if(studentData.getBinding()==StaticGlobal.NOBIND) {
					return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该学生已经解绑"));
				}
				studentData.setBinding(StaticGlobal.NOBIND);
				studentDataService.updateStudentBindingByIdentityCardOrStrudentCode(studentData);
				return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("该学生解绑成功"));
			}
			
			if(!studentData.getName().equals(studentBindVo.getName())) {
					return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("姓名不匹配学生"));
			}
			if(studentData.getBinding()==StaticGlobal.BIND) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该学生已经绑定"));
			}
			studentData.setBinding(StaticGlobal.BIND);
			studentDataService.updateStudentBindingByIdentityCardOrStrudentCode(studentData);
			StudentInfoVo stuInfoVo=new StudentInfoVo();
			stuInfoVo.setName(studentData.getName());
			stuInfoVo.setWasteType(0);
			stuInfoVo.setUnit(0);
			int points=studentData.getTotalPoints()-studentData.getUsedPoints()-studentData.getDeductPoints();
			stuInfoVo.setPoint(points<0?0:points);
			stuInfoVo.setBind(studentData.getBinding());
			String qrcode=globalDateService.getGlobalKeyString("qrUrl")+"?code="+studentData.getCode()+"&card="+studentData.getCard();
			stuInfoVo.setQrCode(qrcode);
			stuInfoVo.setStuCard(studentData.getCard());
			stuInfoVo.setStuCode(studentData.getCode());
			ResponseMessage<StudentInfoVo> responseMessage = new ResponseMessage<StudentInfoVo>();
			responseMessage.setData(stuInfoVo);
			responseMessage.setSuccessMessage("绑定学生信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/stubindbyphone")
	public String stuBindByPhone(@RequestBody JSONObject message) {
		RequestMessage<StudentBindVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentBindVo>>() {
				});
		StudentBindVo studentBindVo = (StudentBindVo) requestMessage.getData();
		if (studentBindVo == null || StringUtils.isEmpty(studentBindVo.getPhone())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(studentBindVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			StudentData studentData = studentService.selectStudentDataByPhone(studentBindVo.getPhone());
			if (studentData == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("查找不到当前学生"));
			}
			if (studentData.getBinding() == StaticGlobal.BIND) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该学生已经绑定"));
			}
			studentData.setBinding((byte) 1);
			studentDataService.updateStudentBindingByIdentityCardOrStrudentCode(studentData);
			StudentInfoVo stuInfoVo = new StudentInfoVo();
			stuInfoVo.setName(studentData.getName());
			stuInfoVo.setWasteType(0);
			stuInfoVo.setUnit(0);
			int points = studentData.getTotalPoints() - studentData.getUsedPoints() - studentData.getDeductPoints();
			stuInfoVo.setPoint(points < 0 ? 0 : points);
			stuInfoVo.setBind(studentData.getBinding());
			String qrcode = globalDateService.getGlobalKeyString("qrUrl") + "?code=" + studentData.getCode() + "&card="
					+ studentData.getCard();
			stuInfoVo.setQrCode(qrcode);
			stuInfoVo.setStuCard(studentData.getCard());
			stuInfoVo.setStuCode(studentData.getCode());
			ResponseMessage<StudentInfoVo> responseMessage = new ResponseMessage<StudentInfoVo>();
			responseMessage.setData(stuInfoVo);
			responseMessage.setSuccessMessage("绑定学生信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

}
