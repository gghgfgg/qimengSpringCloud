package com.qimeng.main.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qimeng.main.dao.DeviceRecycleCountDao;
import com.qimeng.main.entity.DeviceRecycleCount;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午4:06:13 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceRecycleCountService {
	private static Logger logger = Logger.getLogger(DeviceRecycleCountService.class);

	@Autowired
	DeviceRecycleCountDao deviceRecycleCountDao;

	/**
	 * 插入更新分类回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	public int insertDeviceRecycleCount(DeviceRecycleCount deviceRecycleCount) {
		try {
			return deviceRecycleCountDao.insertDeviceRecycleCount(deviceRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入机器回收数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 查找回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	List<DeviceRecycleCount> selectDeviceRecycleCountList(DeviceRecycleCount deviceRecycleCount){
		try {
			return deviceRecycleCountDao.selectDeviceRecycleCountList(deviceRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找机器回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据machineId 查找回收机
	 * @param uuid
	 * @return
	 */
	List<DeviceRecycleCount> selectDeviceRecycleCountByUuid(String machineId){
		DeviceRecycleCount deviceRecycleCount=new DeviceRecycleCount();
		deviceRecycleCount.setMachineId(machineId);
		return selectDeviceRecycleCountList(deviceRecycleCount);
	}
	
	/**
	 * 根据回收类型  查找回收
	 * @param type
	 * @return
	 */
	List<DeviceRecycleCount> selectDeviceRecycleCountByType(byte type){
		DeviceRecycleCount deviceRecycleCount=new DeviceRecycleCount();
		deviceRecycleCount.setType(type);
		return selectDeviceRecycleCountList(deviceRecycleCount);
	}
	
	/**
	 * 根据回收类型machineId  查找回收
	 * @param type
	 * @return
	 */
	DeviceRecycleCount selectDeviceRecycleCount(String machineId,byte type){
		DeviceRecycleCount deviceRecycleCount=new DeviceRecycleCount();
		deviceRecycleCount.setMachineId(machineId);
		deviceRecycleCount.setType(type);
		List<DeviceRecycleCount> list=selectDeviceRecycleCountList(deviceRecycleCount);
		return list.isEmpty()?null:list.get(0);
	}
}

