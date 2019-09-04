package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DeviceManagementDao;
import com.qimeng.main.entity.DeviceManagement;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午3:34:19 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Service
public class DeviceManagementService {
	private static Logger logger = Logger.getLogger(DeviceManagementService.class); 
	@Autowired
	DeviceManagementDao deviceManagementDao;
	
	/**
	 * @param deviceManagement
	 * @return
	 */
	@CacheEvict(value="DeviceManagement",key="#p0.machineId")
	public int insertDeviceManagement(DeviceManagement deviceManagement) {
		try {
			return deviceManagementDao.insertDeviceManagement(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置新的回收设备异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param deviceManagement
	 * @return
	 */
	@CacheEvict(value="DeviceManagement",key="#p0.machineId")
	public int updateDeviceManagement(DeviceManagement deviceManagement) {
		try {
			return deviceManagementDao.updateDeviceManagement(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新回收设备异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param deviceManagement
	 * @return
	 */
	public List<DeviceManagement> selectDeviceManagementList(DeviceManagement deviceManagement){
		try {
			return deviceManagementDao.selectDeviceManagementList(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询回收设备参数异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param schoolCode
	 * @return
	 */
	public List<DeviceManagement> selectDeviceManagementListBySchoolCode(String schoolCode){
		DeviceManagement deviceManagement=new DeviceManagement();
		deviceManagement.setSchoolCode(schoolCode);
		return selectDeviceManagementList(deviceManagement);
	}
	public List<DeviceManagement> selectDeviceManagementListByPostalCode(String postalCode){
		DeviceManagement deviceManagement=new DeviceManagement();
		deviceManagement.setPostalCode(postalCode);
		return selectDeviceManagementList(deviceManagement);
	}
	
	@Cacheable(value="DeviceManagement",key="#machineId",unless="#result == null")
	public DeviceManagement selectDeviceManagementListByMachineId(String machineId){
		DeviceManagement deviceManagement=new DeviceManagement();
		deviceManagement.setMachineId(machineId);
		List<DeviceManagement> list=selectDeviceManagementList(deviceManagement);
		return list.isEmpty()?null:list.get(0);
	}
	
	@Cacheable(value="DeviceManagement",key="#machineId",unless="#result == null")
	public DeviceManagement selectDeviceManagementListByMachineId(String machineId,boolean active){
		DeviceManagement deviceManagement=new DeviceManagement();
		deviceManagement.setMachineId(machineId);
		deviceManagement.setActive(active);
		List<DeviceManagement> list=selectDeviceManagementList(deviceManagement);
		return list.isEmpty()?null:list.get(0);
	}

	@CacheEvict(value="DeviceManagement",key="#p0.machineId")
	public int updateDeviceMachineId(DeviceManagement deviceManagement) {
		try {
			return deviceManagementDao.updateDeviceMachineId(deviceManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新回收设备序列号异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
}

