package com.qimeng.main.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceRecycleOrderDao;
import com.qimeng.main.entity.DeviceRecycleOrder;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月17日 下午3:35:59 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DeviceRecycleOrderService {
private static Logger logger = Logger.getLogger(DeviceRecycleOrderService.class);
	
	@Autowired
	DeviceRecycleOrderDao deviceRecycleOrderDao;
	
	public int insertDeviceRecycleOrder(DeviceRecycleOrder deviceRecycleOrder) {
		try {
			return deviceRecycleOrderDao.insertDeviceRecycleOrder(deviceRecycleOrder);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增回收单数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public int updateDeviceRecycleOrder(DeviceRecycleOrder deviceRecycleOrder) {
		try {
			return deviceRecycleOrderDao.updateDeviceRecycleOrder(deviceRecycleOrder);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新回收单数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	int updateDeviceRecycleOrderEnd(DeviceRecycleOrder deviceRecycleOrder) {
		DeviceRecycleOrder temp=new DeviceRecycleOrder();
		temp.setbEnd(true);;
		temp.setUuid(deviceRecycleOrder.getUuid());
		temp.setMachineId(deviceRecycleOrder.getMachineId());
		temp.setEndTime(deviceRecycleOrder.getEndTime());
		return updateDeviceRecycleOrder(temp);
	}
	
	int updateDeviceRecycleOrderCount(DeviceRecycleOrder deviceRecycleOrder) {
		DeviceRecycleOrder temp=new DeviceRecycleOrder();
		temp.setMachineId(deviceRecycleOrder.getMachineId());
		temp.setUuid(deviceRecycleOrder.getUuid());
		temp.setRealCount(deviceRecycleOrder.getRealCount());
		temp.setUploadTime(deviceRecycleOrder.getUploadTime());
		return updateDeviceRecycleOrder(temp);
	}
}

