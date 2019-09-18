package com.qimeng.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.qimeng.main.entity.GlobalDate;
import com.qimeng.main.entity.SchoolId;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.GlobalDateService;
import com.qimeng.main.service.SchoolIdService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.KeyValueVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月5日 下午3:43:24 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/sysinfo")
public class KeyValueController {
	private static Logger logger = Logger.getLogger(KeyValueController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	GlobalDateService globalDateService;
	@Autowired
	SchoolIdService shoolIdService;
	
	@RequestMapping("/globaldatelist/{page}")
	public String globaldateList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<GlobalDate> globalDatePageInfo=globalDateService.selectGlobalDatePageList(page);
			ResponseMessage<PageInfo<GlobalDate>> responseMessage = new ResponseMessage<PageInfo<GlobalDate>>();
			responseMessage.setData(globalDatePageInfo);
			responseMessage.setSuccessMessage("获取系统全局信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/schoolidlist/{page}")
	public String schoolIdPageList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<SchoolId> schoolIdPageInfo=shoolIdService.selectSchoolIdPageList(page);
			ResponseMessage<PageInfo<SchoolId>> responseMessage = new ResponseMessage<PageInfo<SchoolId>>();
			responseMessage.setData(schoolIdPageInfo);
			responseMessage.setSuccessMessage("获取学校id信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/schoolidlist")
	public String schoolIdList( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			List<SchoolId> schoolIdList=shoolIdService.selectSchoolIdList();
			List<KeyValueVo> list=new ArrayList<KeyValueVo>();
			for (SchoolId schoolId : schoolIdList) {
				KeyValueVo item=new KeyValueVo();
				item.setStrKey(schoolId.getSchoolId());
				item.setStrValue(schoolId.getSchoolName());
				list.add(item);
			}
			ResponseMessage<List<KeyValueVo>> responseMessage = new ResponseMessage<List<KeyValueVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取学校id信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/globaldate")
	public String globaldate( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||keyValueVo.getId()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			GlobalDate globalDate=globalDateService.selectGlobalDate(keyValueVo.getId());
			keyValueVo.setStrKey(globalDate.getGlobalKey());
			keyValueVo.setStrValue(globalDate.getGlobalValue());
			ResponseMessage<KeyValueVo> responseMessage = new ResponseMessage<KeyValueVo>();
			responseMessage.setData(keyValueVo);
			responseMessage.setSuccessMessage("获取全局信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	@RequestMapping("/globaldatebykey")
	public String globaldateByKey( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||StringUtils.isEmpty(keyValueVo.getStrKey())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			keyValueVo.setStrValue(globalDateService.getGlobalKeyString(keyValueVo.getStrKey()));
			ResponseMessage<KeyValueVo> responseMessage = new ResponseMessage<KeyValueVo>();
			responseMessage.setData(keyValueVo);
			responseMessage.setSuccessMessage("获取全局信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	@RequestMapping("/schoolId")
	public String schoolId( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||keyValueVo.getId()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			SchoolId schoolId=shoolIdService.selectSchoolId(keyValueVo.getId());
			keyValueVo.setStrKey(schoolId.getSchoolId());
			keyValueVo.setStrValue(schoolId.getSchoolName());
			ResponseMessage<KeyValueVo> responseMessage = new ResponseMessage<KeyValueVo>();
			responseMessage.setData(keyValueVo);
			responseMessage.setSuccessMessage("获取学校id信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/saveglobaldate")
	public String saveGlobaldate( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||StringUtils.isEmpty(keyValueVo.getStrKey())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			GlobalDate globalDate=new GlobalDate();
			globalDate.setGlobalKey(keyValueVo.getStrKey());
			globalDate.setGlobalValue(keyValueVo.getStrValue());
			Date date=new Date();
			globalDate.setCreateTime(date);
			globalDate.setUpdateTime(date);
			globalDateService.insertGlobalDate(globalDate);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增全局信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/saveschoolId")
	public String saveSchoolId( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||StringUtils.isEmpty(keyValueVo.getStrKey())) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			SchoolId schoolId=new SchoolId();
			schoolId.setSchoolId(keyValueVo.getStrKey());
			schoolId.setSchoolName(keyValueVo.getStrValue());
			Date date=new Date();
			schoolId.setCreateTime(date);
			schoolId.setUpdateTime(date);
			shoolIdService.insertSchoolId(schoolId);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增学校id信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updateglobaldate")
	public String updateGlobaldate( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||keyValueVo.getId()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			GlobalDate globalDate=new GlobalDate();
			globalDate.setId(keyValueVo.getId());
			globalDate.setGlobalKey(keyValueVo.getStrKey());
			globalDate.setGlobalValue(keyValueVo.getStrValue());
			Date date=new Date();
			globalDate.setUpdateTime(date);
			globalDateService.updateGlobalDate(globalDate);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新全局信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updateschoolId")
	public String updateSchoolId( @RequestBody JSONObject message) {
		RequestMessage<KeyValueVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<KeyValueVo>>() {
				});
		KeyValueVo keyValueVo = (KeyValueVo) requestMessage.getData();
		if(keyValueVo==null||keyValueVo.getId()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(keyValueVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			SchoolId schoolId=new SchoolId();
			schoolId.setId(keyValueVo.getId());
			schoolId.setSchoolId(keyValueVo.getStrKey());
			schoolId.setSchoolName(keyValueVo.getStrValue());
			Date date=new Date();
			schoolId.setUpdateTime(date);
			shoolIdService.updateSchoolId(schoolId);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学校id信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
}

