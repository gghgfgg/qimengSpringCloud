package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.SchoolService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.SchoolInfoVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月13日 下午2:49:07 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/schoolinfo")
public class SchoolInformController {
	private static Logger logger = Logger.getLogger(SchoolInformController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	SchoolService schoolService;
	
	@RequestMapping("/getschoollist/{page}")
	public String getSchoolList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			PageInfo<SchoolInfoVo> schoolPageInfo = schoolService.schoolPageList(page, schoolInfoVo);
			
			ResponseMessage<PageInfo<SchoolInfoVo>> responseMessage = new ResponseMessage<PageInfo<SchoolInfoVo>>();
			responseMessage.setData(schoolPageInfo);
			responseMessage.setSuccessMessage("获取学校列表信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	@RequestMapping("/getschoolinfo")
	public String getSchoolinfo(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			SchoolInfoVo schoolInfo = schoolService.schoolInform(schoolInfoVo);
			
			ResponseMessage<SchoolInfoVo> responseMessage = new ResponseMessage<SchoolInfoVo>();
			responseMessage.setData(schoolInfo);
			responseMessage.setSuccessMessage("获取学校信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
	
	
	@RequestMapping("/saveschool")
	public String saveSchoolinfo(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			schoolService.saveSchoolinfo(schoolInfoVo);

			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("保存学校信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}

	@RequestMapping("/updateschool")
	public String updateSchool(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null || (applicationManagement.getAppType() & StaticGlobal.UPDATE) == 0) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			schoolService.updateSchoolinfo(schoolInfoVo);

			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setSuccessMessage("更新学校信息成功");
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

