package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			PageInfo<SchoolInfoVo> schoolPageInfo = schoolService.schoolPageList(page, schoolInfoVo);
			
			ResponseMessage<PageInfo<SchoolInfoVo>> responseMessage = new ResponseMessage<PageInfo<SchoolInfoVo>>();
			responseMessage.setData(schoolPageInfo);
			responseMessage.setSuccessMessage("获取学校列表信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/getschoolinfo")
	public String getSchoolinfo(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		if(schoolInfoVo==null||StringUtils.isEmpty(schoolInfoVo.getSchoolCode())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			SchoolInfoVo schoolInfo = schoolService.schoolInform(schoolInfoVo);
			
			ResponseMessage<SchoolInfoVo> responseMessage = new ResponseMessage<SchoolInfoVo>();
			responseMessage.setData(schoolInfo);
			responseMessage.setSuccessMessage("获取学校信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	
	@RequestMapping("/saveschool")
	public String saveSchoolinfo(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		if(schoolInfoVo==null||StringUtils.isEmpty(schoolInfoVo.getSchoolCode())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			schoolService.saveSchoolinfo(schoolInfoVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增学校信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}

	@RequestMapping("/updateschool")
	public String updateSchool(@RequestBody JSONObject message) {
		RequestMessage<SchoolInfoVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<SchoolInfoVo>>() {
				});
		SchoolInfoVo schoolInfoVo = (SchoolInfoVo) requestMessage.getData();
		if(schoolInfoVo==null||StringUtils.isEmpty(schoolInfoVo.getSchoolCode())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(schoolInfoVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}

			schoolService.updateSchoolinfo(schoolInfoVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学校信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
}

