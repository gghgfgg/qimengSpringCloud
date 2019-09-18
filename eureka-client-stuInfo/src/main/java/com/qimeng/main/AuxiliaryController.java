package com.qimeng.main;

import java.util.ArrayList;
import java.util.List;

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
import com.qimeng.main.entity.DeviceState;
import com.qimeng.main.entity.RecycleType;
import com.qimeng.main.entity.SchoolContactsType;
import com.qimeng.main.entity.StudentType;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.AuxiliaryService;
import com.qimeng.main.service.DeviceStateService;
import com.qimeng.main.service.RecycleTypeService;
import com.qimeng.main.service.SchoolContactsTypeService;
import com.qimeng.main.service.StudentTypeService;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.AuxiliaryVo;
import com.qimeng.main.vo.KeyValueVo;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月20日 上午9:20:28 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/auxiliary")
public class AuxiliaryController {
private static Logger logger = Logger.getLogger(AuxiliaryController.class);
	
	@Autowired
	ApplicationManagementService applicationManagementService;
	@Autowired
	AuxiliaryService auxiliaryService;
	@Autowired
	StudentTypeService studentTypeService;
	@Autowired
	DeviceStateService deviceStateService;
	@Autowired
	RecycleTypeService recycleTypeService;
	@Autowired
	SchoolContactsTypeService schoolContactsTypeService;
	
	@RequestMapping("/devstatelist/{page}")
	public String devStateList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<DeviceState> auxiliaryPageInfo=auxiliaryService.auxiliaryDeviceStatePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<DeviceState>> responseMessage = new ResponseMessage<PageInfo<DeviceState>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/devstatelist")
	public String devStateList( @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			List<DeviceState> deviceStatelist=deviceStateService.selectDeviceStateList(new DeviceState());
			List<KeyValueVo> list=new ArrayList<KeyValueVo>();
			for (DeviceState deviceState : deviceStatelist) {
				KeyValueVo item=new KeyValueVo();
				item.setStrKey(deviceState.getStatus()+"-"+deviceState.getType());
				item.setStrValue(deviceState.getMark());
				list.add(item);
			}
			ResponseMessage<List<KeyValueVo>> responseMessage = new ResponseMessage<List<KeyValueVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/stutypelist/{page}")
	public String stuTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<StudentType> auxiliaryPageInfo=auxiliaryService.auxiliaryStudentTypePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<StudentType>> responseMessage = new ResponseMessage<PageInfo<StudentType>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取学生类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/stutypelist")
	public String stuTypeList(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			List<StudentType> studentTypelist = studentTypeService.selectStudentTypeList(new StudentType());
			List<KeyValueVo> list=new ArrayList<KeyValueVo>();
			for (StudentType studentType : studentTypelist) {
				KeyValueVo item=new KeyValueVo();
				item.setStrKey(studentType.getType().toString());
				item.setStrValue(studentType.getMark());
				list.add(item);
			}
			ResponseMessage<List<KeyValueVo>> responseMessage = new ResponseMessage<List<KeyValueVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取学生类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/recycletypelist/{page}")
	public String recycleTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<RecycleType> auxiliaryPageInfo=auxiliaryService.auxiliaryRecycleTypePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<RecycleType>> responseMessage = new ResponseMessage<PageInfo<RecycleType>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/recycletypelist")
	public String recycleTypeList(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			List<RecycleType> recycleTypelist=recycleTypeService.selectRecycleTypeList(new RecycleType());
			List<KeyValueVo> list=new ArrayList<KeyValueVo>();
			for (RecycleType recycleType : recycleTypelist) {
				KeyValueVo item=new KeyValueVo();
				item.setStrKey(recycleType.getType().toString());
				item.setStrValue(recycleType.getMark());
				list.add(item);
			}
			ResponseMessage<List<KeyValueVo>> responseMessage = new ResponseMessage<List<KeyValueVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/contactstypelist/{page}")
	public String contactsTypeList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			PageInfo<SchoolContactsType> auxiliaryPageInfo=auxiliaryService.auxiliaryContactsTypePageList(page,auxiliaryVo);
			ResponseMessage<PageInfo<SchoolContactsType>> responseMessage = new ResponseMessage<PageInfo<SchoolContactsType>>();
			responseMessage.setData(auxiliaryPageInfo);
			responseMessage.setSuccessMessage("获取联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	@RequestMapping("/contactstypelist")
	public String contactsTypeList(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			List<SchoolContactsType> schoolContactsTypelist=schoolContactsTypeService.selectSchoolContactsType(new SchoolContactsType());
			List<KeyValueVo> list=new ArrayList<KeyValueVo>();
			for (SchoolContactsType schoolContactsType : schoolContactsTypelist) {
				KeyValueVo item=new KeyValueVo();
				item.setStrKey(schoolContactsType.getType().toString());
				item.setStrValue(schoolContactsType.getPosition());
				list.add(item);
			}
			ResponseMessage<List<KeyValueVo>> responseMessage = new ResponseMessage<List<KeyValueVo>>();
			responseMessage.setData(list);
			responseMessage.setSuccessMessage("获取联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/devstate")
	public String devState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getStatus()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryDeviceState(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取设备状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/stutype")
	public String stutype(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryStudentType(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取学生状态信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/recycletype")
	public String recycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryRecycleType(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取回收类型信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/contactstype")
	public String contactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.READ);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			AuxiliaryVo returnVo=auxiliaryService.auxiliaryContactsType(auxiliaryVo);
			ResponseMessage<AuxiliaryVo> responseMessage = new ResponseMessage<AuxiliaryVo>();
			responseMessage.setData(returnVo);
			responseMessage.setSuccessMessage("获取联系人信息成功");
			return JSONObject.toJSONString(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	
	@RequestMapping("/savedevstate")
	public String saveDevState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getStatus()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.saveDeviceState(auxiliaryVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增设备状态信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/savestutype")
	public String saveStuType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.saveStudentType(auxiliaryVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增学生类型信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/saverecycletype")
	public String saveRecycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getFactor()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.saveRecycleType(auxiliaryVo);
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增回收类型信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/savecontactstype")
	public String saveContactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getType()==null||auxiliaryVo.getWeight()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.saveContactsType(auxiliaryVo);
		
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("新增联系人信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	
	@RequestMapping("/updatedevstate")
	public String updateDevState(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getStatus()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.updateDevState(auxiliaryVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新设备状态信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updatestutype")
	public String updateStuType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.updateStudentType(auxiliaryVo);

			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新学生类型信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updaterecycletype")
	public String updateRecycleType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getFactor()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.updateRecycleType(auxiliaryVo);
			
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新回收类型信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
	
	@RequestMapping("/updatecontactstype")
	public String updateContactsType(@RequestBody JSONObject message) {
		RequestMessage<AuxiliaryVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<AuxiliaryVo>>() {
				});
		AuxiliaryVo auxiliaryVo = (AuxiliaryVo) requestMessage.getData();
		if(auxiliaryVo==null||auxiliaryVo.getId()==null||auxiliaryVo.getType()==null||auxiliaryVo.getWeight()==null) {
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("参数不足"));
		}
		logger.debug(auxiliaryVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.checkApplicationAuthority(requestMessage.getAppID(), StaticGlobal.UPDATE);
			if (applicationManagement == null) {
				return JSONObject.toJSONString(ResponseMessage.responseFailedMessage("该appid没有权限"));
			}
			
			auxiliaryService.updateContactsType(auxiliaryVo);
			return JSONObject.toJSONString(ResponseMessage.responseSuccessMessage("更新联系人信息成功"));
		} catch (Exception e) {
			// TODO: handle exception
			return JSONObject.toJSONString(ResponseMessage.responseFailedMessage(e.toString()));
		}
	}
}

