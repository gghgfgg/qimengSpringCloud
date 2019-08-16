package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceStateDao;
import com.qimeng.main.entity.DeviceState;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午11:21:03 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceStateService {
	private static Logger logger = Logger.getLogger(DeviceStateService.class);
	@Autowired
	DeviceStateDao deviceStateDao;
	@CacheEvict(value="DeviceState",key="#p0.type+'-'+#p0.status")
	public int insertDeviceState(DeviceState deviceState) {
		try {
			return deviceStateDao.insertDeviceState(deviceState);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增设备状态错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	@CacheEvict(value="DeviceState",key="#p0.type+'-'+#p0.status")
	public int updateDeviceState(DeviceState deviceState) {
		try {
			return deviceStateDao.updateDeviceState(deviceState);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新设备状态错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	
	public List<DeviceState> selectDeviceStateList(DeviceState deviceState) {
		try {
			return deviceStateDao.selectDeviceStateList(deviceState);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("选择设备状态错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	@Cacheable(value="DeviceState",key="#type+'-'+#status")
	public DeviceState selectDeviceState(byte type,byte status) {
		DeviceState deviceState=new DeviceState();
		deviceState.setType(type);
		deviceState.setStatus(status);
		List<DeviceState> list=selectDeviceStateList(deviceState);
		return list.isEmpty()?null:list.get(0);
	}
}

