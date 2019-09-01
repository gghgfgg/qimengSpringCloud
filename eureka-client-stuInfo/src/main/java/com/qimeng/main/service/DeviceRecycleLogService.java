package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceRecycleLogDao;
import com.qimeng.main.entity.DeviceRecycleLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午5:05:01 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceRecycleLogService {
	private static Logger logger = Logger.getLogger(DeviceRecycleLogService.class); 
	@Autowired
	DeviceRecycleLogDao deviceRecycleLogDao;
	
	/**
	 * @param deviceRecycleLog
	 * @return
	 */
	public int insertDeviceRecycleLog(DeviceRecycleLog deviceRecycleLog) {
		try {
			return deviceRecycleLogDao.insertDeviceRecycleLog(deviceRecycleLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入机器回收日志数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<DeviceRecycleLog> selectDeviceRecycleLogList(DeviceRecycleLog deviceRecycleLog){
		try {
			return deviceRecycleLogDao.selectDeviceRecycleLogList(deviceRecycleLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询机器回收日志数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<DeviceRecycleLog> selectDeviceRecycleLogBySchoolDay(String schoolcode,byte type){
		try {
			return deviceRecycleLogDao.selectDeviceRecycleLogBySchoolDay(schoolcode,type);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询机器回收日志数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<DeviceRecycleLog> selectDeviceRecycleLogListByUuid(String uuid){
		DeviceRecycleLog deviceRecycleLog =new DeviceRecycleLog();
		deviceRecycleLog.setUuid(uuid);
		return selectDeviceRecycleLogList(deviceRecycleLog);
	}
}

