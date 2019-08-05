package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceCurrentStateDao;
import com.qimeng.main.entity.DeviceCurrentState;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月4日 上午12:14:15 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceCurrentStateService {
	private static Logger logger = Logger.getLogger(DeviceCurrentStateService.class);
	
	@Autowired
	DeviceCurrentStateDao deviceCurrentStateDao;
	
	public int insertDeviceStateLog(DeviceCurrentState deviceCurrentState) {
		try {
			return deviceCurrentStateDao.insertDeviceCurrentState(deviceCurrentState);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新设备状态错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);	
		}
	}
	
	public List<DeviceCurrentState> selectDeviceStateLog(DeviceCurrentState deviceCurrentState){
		try {
			return deviceCurrentStateDao.selectDeviceCurrentStateList(deviceCurrentState);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询设备状态错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);	
		}
	}
	
	public DeviceCurrentState selectDeviceStateLogBySerialNumber(String serialNumber) {
		DeviceCurrentState deviceCurrentState=new DeviceCurrentState();
		deviceCurrentState.setSerialNumber(serialNumber);
		List<DeviceCurrentState> list=selectDeviceStateLog(deviceCurrentState);
		return list.isEmpty()?null:list.get(0);
	}
}

