package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceRecycleRealTimeDao;
import com.qimeng.main.entity.DeviceRecycleRealTime;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月17日 下午3:35:22 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceRecycleRealTimeService {
	private static Logger logger = Logger.getLogger(DeviceRecycleRealTimeService.class);
	
	@Autowired
	DeviceRecycleRealTimeDao deviceRecycleRealTimeDao;
	
	public int insertDeviceRecycleRealTime(DeviceRecycleRealTime deviceRecycleRealTime) {
		try {
			return deviceRecycleRealTimeDao.insertDeviceRecycleRealTime(deviceRecycleRealTime);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增实时回收数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public int updateDeviceRecycleRealTime(DeviceRecycleRealTime deviceRecycleRealTime) {
		try {
			return deviceRecycleRealTimeDao.updateDeviceRecycleRealTime(deviceRecycleRealTime);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新实时回收数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<DeviceRecycleRealTime> selectDeviceRecycleRealTimeList(DeviceRecycleRealTime deviceRecycleRealTime) {
		try {
			return deviceRecycleRealTimeDao.selectDeviceRecycleRealTimeList(deviceRecycleRealTime);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询实时回收数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public DeviceRecycleRealTime selectDeviceRecycleRealTime(String machineId,Byte type) {
		DeviceRecycleRealTime deviceRecycleRealTime=new DeviceRecycleRealTime();
		deviceRecycleRealTime.setMachineId(machineId);
		deviceRecycleRealTime.setType(type);
		deviceRecycleRealTime.setbRecycle(false);
		List<DeviceRecycleRealTime> list=selectDeviceRecycleRealTimeList(deviceRecycleRealTime);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public int updateDeviceRecycleRealTimeRecycle(DeviceRecycleRealTime deviceRecycleRealTime) {
		DeviceRecycleRealTime temp=new DeviceRecycleRealTime();
		temp.setId(deviceRecycleRealTime.getId());
		temp.setbRecycle(true);
		temp.setEndTime(deviceRecycleRealTime.getEndTime());
		return updateDeviceRecycleRealTime(temp);
	}
	
	public int updateDeviceRecycleRealTimeCount(DeviceRecycleRealTime deviceRecycleRealTime) {
		DeviceRecycleRealTime temp=new DeviceRecycleRealTime();
		temp.setId(deviceRecycleRealTime.getId());
		temp.setCount(deviceRecycleRealTime.getCount());
		return updateDeviceRecycleRealTime(temp);
	}
}

