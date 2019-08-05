package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceStateLogDao;
import com.qimeng.main.entity.DeviceStateLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午11:53:13 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceStateLogService {
	private static Logger logger = Logger.getLogger(DeviceStateLogService.class);
	@Autowired
	DeviceStateLogDao deviceStateLogDao;
	
	public int insertDeviceStateLog(DeviceStateLog deviceStateLog) {
		try {
			return deviceStateLogDao.insertDeviceStateLog(deviceStateLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增设备状态日志错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);	
		}
	}
	
	public List<DeviceStateLog> selectDeviceStateLog(DeviceStateLog deviceStateLog){
		try {
			return deviceStateLogDao.selectDeviceStateLog(deviceStateLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询设备状态日志错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);	
		}
	}
	
}

