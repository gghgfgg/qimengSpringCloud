package com.qimeng.main;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.entity.ExcelRecycle;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.StudentUpdatePointsService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.util.UpLoadFile;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentVo;
import com.qimeng.main.vo.UsedPointsVo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年9月26日 上午10:08:45
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
@RequestMapping("/updatepoint")
public class UpdatePointByExcel {
	private static Logger logger = Logger.getLogger(UpdatePointByExcel.class);

	@Autowired
	UpLoadFile upLoadFile;
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	StudentUpdatePointsService studentUpdatePointsService;
	
	@RequestMapping("/uploadpoint")
	public String uploadPoints(String message, MultipartFile file) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message,
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		if (studentVo == null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		if (file == null || file.getSize() == 0) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("无效文件"));
		}
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.ADD);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			String fileName = file.getOriginalFilename(); // 文件名
			File uploadFlie = upLoadFile.getUploadFile(fileName, requestMessage.getAppID(),
					requestMessage.getOperator());
			file.transferTo(uploadFlie);

			List<ExcelRecycle> list = upLoadFile.uploadPoint(uploadFlie);
			int updatecount = studentUpdatePointsService.updateStudentPointsByExcel(list);
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
	
	@RequestMapping("/usedpoint")
	public String usedPoints(String message, MultipartFile file) {
		RequestMessage<UsedPointsVo> requestMessage = JSON.parseObject(message,
				new TypeReference<RequestMessage<UsedPointsVo>>() {
				});
		UsedPointsVo usedPointsVo = (UsedPointsVo) requestMessage.getData();
		if (usedPointsVo == null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		if (file == null || file.getSize() == 0) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("无效文件"));
		}
		logger.debug(usedPointsVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.SUB);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			String fileName = file.getOriginalFilename(); // 文件名
			File uploadFlie = upLoadFile.getUploadFile(fileName, requestMessage.getAppID(),
					requestMessage.getOperator());
			file.transferTo(uploadFlie);

			List<ExcelRecycle> list = upLoadFile.uploadPoint(uploadFlie);
			int updatecount = studentUpdatePointsService.updateStudentUsedPointsByExcel(list,usedPointsVo.getMark(),requestMessage.getAppID());
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
}
