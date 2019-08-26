package com.qimeng.main.service;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.DeviceCurrentState;
import com.qimeng.main.entity.DeviceManagement;
import com.qimeng.main.entity.DeviceState;
import com.qimeng.main.entity.DeviceStateLog;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.vo.DeviceInformVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月8日 下午9:06:53 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Transactional
@Service
public class DeviceActionService {
	
	@Autowired
	DeviceStateLogService deviceStateLogService;
	@Autowired
	DeviceStateService deviceStateService;
	@Autowired
	DeviceCurrentStateService deviceCurrentStateService;
	@Autowired
	DeviceManagementService deviceManagementService;
	@Autowired
	SchoolInformService schoolInformService;
	@Autowired
	JoinDao joinDao;
	
	@Autowired
	PostalCodeService postalCodeService;
	
	public int updateDevStatus(String serialNumber,Byte status,Byte type) {
		try {
			Date date=new Date();
			DeviceState deviceState=deviceStateService.selectDeviceState(type,status);
	
			DeviceStateLog deviceStateLog=new DeviceStateLog();
			deviceStateLog.setSerialNumber(serialNumber);
			deviceStateLog.setStatus(status);
			deviceStateLog.setStatusType(type);
			deviceStateLog.setMark(deviceState==null?"":deviceState.getMark());
			deviceStateLog.setCreateTime(date);
			deviceStateLogService.insertDeviceStateLog(deviceStateLog);
			
			DeviceCurrentState deviceCurrentState=new DeviceCurrentState();
			deviceCurrentState.setSerialNumber(serialNumber);
			deviceCurrentState.setStatus(status);
			deviceCurrentState.setStatusType(type);
			deviceCurrentState.setCreateTime(date);
			deviceCurrentState.setUpdateTime(date);
			deviceCurrentStateService.insertDeviceStateLog(deviceCurrentState);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public int updateMachineId(String machineId,String serialNumber) {
		try {
			DeviceManagement deviceManagement=deviceManagementService.selectDeviceManagementListByMachineId(machineId);
			if(!StringUtils.isEmpty(deviceManagement.getSerialNumber())&&!StringUtils.isEmpty(serialNumber)){
				throw new RuntimeException("请先解绑序列号");
			}
			Date date=new Date();
			deviceManagement=new DeviceManagement();
			deviceManagement.setMachineId(machineId);
			deviceManagement.setSerialNumber(serialNumber);
			deviceManagement.setUpdateTime(date);
			return deviceManagementService.updateDeviceMachineId(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public PageInfo<DeviceInformVo> selectDevPageList(Integer pageNum,DeviceInformVo devInformVo){
		PageHelper.startPage(pageNum, 20);
		List<DeviceInformVo> news = joinDao.selectDeviceInformVosList(devInformVo);
	    PageInfo<DeviceInformVo> devPageInfo = new PageInfo<>(news);
		return devPageInfo;
	}

	
	public PageInfo<DeviceInformVo> DevPageList(Integer pageNum,DeviceInformVo devInformVo){

		if(!StringUtils.isEmpty(devInformVo.getPostalCode())) {
			String codeString=postalCodeService.selectPostalCode(devInformVo.getPostalCode());
			System.out.println(codeString);
			devInformVo.setPostalCode(codeString);
		}
		if(!StringUtils.isEmpty(devInformVo.getSchoolName())) {
			 String schoolcode=new String();
			 List<SchoolInform> schoollist=schoolInformService.selectSchoolInformByName(devInformVo.getSchoolName());
			 for (SchoolInform schoolInform : schoollist) {
				 schoolcode+=schoolInform.getSchoolCode();
				 schoolcode+="-";
			}
			if(devInformVo.getSchoolCode()!=null) {
				schoolcode=devInformVo.getSchoolCode()+"-"+schoolcode;
			}
			devInformVo.setSchoolCode(schoolcode);
		}
	    PageInfo<DeviceInformVo> devPageInfo = selectDevPageList(pageNum,devInformVo);
	    Iterator<DeviceInformVo> it = devPageInfo.getList().iterator();
	    while(it.hasNext()){
		  DeviceInformVo item=it.next();
		  if(item.getActive()) {
//	    		Date date=new Date();
//	    		long timeout=date.getTime()-item.getUpdateTime().getTime();
//	    		if(item.getStatus()==1&&timeout>1000*60*30) {
//	    			updateDevStatus(item.getSerialNumber(),(byte)2,(byte)0);
//	    			item.setStatus((byte)2);
//	    			item.setType((byte)0);
//	    			item.setUpdateTime(date);
//	    		}
//	    		if(devInformVo.getStatus()!=null&&devInformVo.getStatus()!=(byte)2) {
//	    			it.remove();
//	    		}
	    	}
		  DeviceState deviceState=deviceStateService.selectDeviceState(item.getType(),item.getStatus());
		  item.setMark(deviceState.getMark());
		  SchoolInform schoolInform=schoolInformService.selectSchoolInformBySchoolCode(item.getSchoolCode());
		  item.setAddress(schoolInform.getAddress());
	    }
	    return devPageInfo;
	}
	
	public int updateDev(DeviceInformVo devInformVo) {
		try {
			Date date=new Date();
			DeviceManagement deviceManagement=new DeviceManagement();
			deviceManagement.setMachineId(devInformVo.getMachineID());
			deviceManagement.setActive(devInformVo.getActive());
			if(!StringUtils.isEmpty(devInformVo.getSchoolCode())) {
				deviceManagement.setSchoolCode(devInformVo.getSchoolCode());
				SchoolInform schoolInform=schoolInformService.selectSchoolInformBySchoolCode(devInformVo.getSchoolCode());
				deviceManagement.setPostalCode(schoolInform.getPostalCode());
			}		
			deviceManagement.setUpdateTime(date);
			return deviceManagementService.updateDeviceManagement(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public DeviceInformVo selectDeviceInformVoByMachineID(String machineId) {
		DeviceManagement deviceManagement=deviceManagementService.selectDeviceManagementListByMachineId(machineId);
		if(deviceManagement==null)
			return null;
		DeviceInformVo deviceInformVo=new DeviceInformVo();
		
		deviceInformVo.setActive(deviceManagement.getActive());
		deviceInformVo.setSerialNumber(deviceManagement.getSerialNumber());
		deviceInformVo.setSchoolCode(deviceManagement.getSchoolCode());
		deviceInformVo.setMachineID(deviceManagement.getMachineId());
		deviceInformVo.setPostalCode(deviceManagement.getPostalCode());
		SchoolInform schoolInform=schoolInformService.selectSchoolInformBySchoolCode(deviceManagement.getSchoolCode());
		deviceInformVo.setSchoolName(schoolInform.getSchoolName());
		deviceInformVo.setAddress(schoolInform.getAddress());
		
		DeviceCurrentState deviceCurrentState=deviceCurrentStateService.selectDeviceStateLogBySerialNumber(deviceManagement.getSerialNumber());
		deviceInformVo.setStatus(deviceCurrentState.getStatus());
		deviceInformVo.setType(deviceCurrentState.getStatusType());
		DeviceState deviceState=deviceStateService.selectDeviceState(deviceCurrentState.getStatus(),deviceCurrentState.getStatusType());
		deviceInformVo.setMark(deviceState.getMark());
		deviceInformVo.setUpdateTime(deviceCurrentState.getUpdateTime());
		return deviceInformVo;
	}
	
	public int saveDev(DeviceInformVo devInformVo) {
		try {
			Date date=new Date();
			DeviceManagement deviceManagement=new DeviceManagement();
			deviceManagement.setMachineId(devInformVo.getMachineID());
			deviceManagement.setActive(devInformVo.getActive());
			if(!StringUtils.isEmpty(devInformVo.getSchoolCode())) {
				deviceManagement.setSchoolCode(devInformVo.getSchoolCode());
				SchoolInform schoolInform=schoolInformService.selectSchoolInformBySchoolCode(devInformVo.getSchoolCode());
				deviceManagement.setPostalCode(schoolInform.getPostalCode());
			}
			deviceManagement.setCreateTime(date);
			deviceManagement.setUpdateTime(date);
			return deviceManagementService.insertDeviceManagement(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

