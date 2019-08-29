package com.qimeng.main.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.entity.DeviceState;
import com.qimeng.main.entity.RecycleType;
import com.qimeng.main.entity.SchoolContactsType;
import com.qimeng.main.vo.AuxiliaryVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月20日 上午9:33:52 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class AuxiliaryService {
	@Autowired
	DeviceStateService deviceStateService;
	@Autowired
	RecycleTypeService recycleTypeService;
	@Autowired
	SchoolContactsTypeService 	schoolContactsTypeService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	public PageInfo<DeviceState> auxiliaryDeviceStatePageList(Integer pageNum, AuxiliaryVo auxiliaryVo) {
		PageHelper.startPage(pageNum, 20);
		DeviceState deviceState=new DeviceState();
		deviceState.setType(auxiliaryVo.getType());
		deviceState.setStatus(auxiliaryVo.getStatus());
        List<DeviceState> news = deviceStateService.selectDeviceStateList(deviceState);
        PageInfo<DeviceState> appsPageInfo = new PageInfo<>(news);
        return appsPageInfo;
	}

	public PageInfo<RecycleType> auxiliaryRecycleTypePageList(Integer pageNum, AuxiliaryVo auxiliaryVo) {
		PageHelper.startPage(pageNum, 20);
		RecycleType recycleType=new RecycleType();
		recycleType.setType(auxiliaryVo.getType());
        List<RecycleType> news = recycleTypeService.selectRecycleTypeList(recycleType);
        PageInfo<RecycleType> appsPageInfo = new PageInfo<>(news);
        return appsPageInfo;
	}

	public PageInfo<SchoolContactsType> auxiliaryContactsTypePageList(Integer pageNum, AuxiliaryVo auxiliaryVo) {
		PageHelper.startPage(pageNum, 20);
		SchoolContactsType contactsType=new SchoolContactsType();
		contactsType.setType(auxiliaryVo.getType());
        List<SchoolContactsType> news = schoolContactsTypeService.selectSchoolContactsType(contactsType);
        PageInfo<SchoolContactsType> appsPageInfo = new PageInfo<>(news);
        return appsPageInfo;
	}

	public AuxiliaryVo auxiliaryDeviceState(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		DeviceState  deviceState=deviceStateService.selectDeviceState(auxiliaryVo.getType(), auxiliaryVo.getStatus());
		auxiliaryVo.setType(deviceState.getType());
		auxiliaryVo.setStatus(deviceState.getStatus());
		auxiliaryVo.setMark(deviceState.getMark());
		auxiliaryVo.setId(deviceState.getId());
		return auxiliaryVo;
	}

	public AuxiliaryVo auxiliaryRecycleType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		RecycleType recycleType=recycleTypeService.selectRecycleTypeByType(auxiliaryVo.getType());
		auxiliaryVo.setType(recycleType.getType());
		auxiliaryVo.setFactor(recycleType.getFactor());
		auxiliaryVo.setMark(recycleType.getMark());
		auxiliaryVo.setUint(recycleType.getUint());
		//auxiliaryVo.setId(recycleType.getId());
		return auxiliaryVo;
	}

	public AuxiliaryVo auxiliaryContactsType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		SchoolContactsType contactsType=schoolContactsTypeService.selectSchoolContactsTypeByType(auxiliaryVo.getType());
		auxiliaryVo.setType(contactsType.getType());
		auxiliaryVo.setWeight(contactsType.getWeight());
		auxiliaryVo.setPosition(contactsType.getPosition());
		//auxiliaryVo.setId(contactsType.getId());
		return auxiliaryVo;
	}

	public int saveDeviceState(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		DeviceState deviceState = new DeviceState();
		deviceState.setType(auxiliaryVo.getType());
		deviceState.setStatus(auxiliaryVo.getStatus());
		deviceState.setMark(auxiliaryVo.getMark());
		Date date = new Date();
		deviceState.setCreateTime(date);
		deviceState.setUpdateTime(date);
		return deviceStateService.insertDeviceState(deviceState);
	}

	public int saveRecycleType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		RecycleType recycleType=new RecycleType();
		recycleType.setType(auxiliaryVo.getType());
		recycleType.setFactor(auxiliaryVo.getFactor());
		recycleType.setUint(auxiliaryVo.getUint());
		recycleType.setMark(auxiliaryVo.getMark());
		Date date = new Date();
		recycleType.setCreateTime(date);
		recycleType.setUpdateTime(date);
		return recycleTypeService.insertRecycleType(recycleType);
	}

	public int saveContactsType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		SchoolContactsType contactsType=new SchoolContactsType();
		contactsType.setType(auxiliaryVo.getType());
		contactsType.setPosition(auxiliaryVo.getPosition());
		contactsType.setWeight(auxiliaryVo.getWeight());
		Date date = new Date();
		contactsType.setCreateTime(date);
		contactsType.setUpdateTime(date);
		return schoolContactsTypeService.insertSchoolContactsType(contactsType);
	}

	public int updateDevState(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		DeviceState tempDeviceState= deviceStateService.selectDeviceState(auxiliaryVo.getId());			
		DeviceState deviceState = new DeviceState();
		deviceState.setId(auxiliaryVo.getId());
		deviceState.setType(auxiliaryVo.getType());
		deviceState.setStatus(auxiliaryVo.getStatus());
		deviceState.setMark(auxiliaryVo.getMark());
		Date date = new Date();
		deviceState.setUpdateTime(date);
		deviceStateService.updateDeviceState(deviceState);
		if(stringRedisTemplate.hasKey("DeviceState::"+tempDeviceState.getType()+"-"+tempDeviceState.getStatus())) {
			stringRedisTemplate.delete("DeviceState::"+tempDeviceState.getType()+"-"+tempDeviceState.getStatus());
		}
		return 1;
	}

	public int updateRecycleType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		RecycleType recycleType=new RecycleType();
		recycleType.setType(auxiliaryVo.getType());
		recycleType.setFactor(auxiliaryVo.getFactor());
		recycleType.setUint(auxiliaryVo.getUint());
		recycleType.setMark(auxiliaryVo.getMark());
		Date date = new Date();
		recycleType.setUpdateTime(date);
		return recycleTypeService.updateRecycleType(recycleType);
	}

	public int updateContactsType(AuxiliaryVo auxiliaryVo) {
		// TODO Auto-generated method stub
		SchoolContactsType contactsType=new SchoolContactsType();
		contactsType.setType(auxiliaryVo.getType());
		contactsType.setPosition(auxiliaryVo.getPosition());
		contactsType.setWeight(auxiliaryVo.getWeight());
		Date date = new Date();
		contactsType.setCreateTime(date);
		contactsType.setUpdateTime(date);
		return schoolContactsTypeService.updateSchoolContactsType(contactsType);
	}

}

