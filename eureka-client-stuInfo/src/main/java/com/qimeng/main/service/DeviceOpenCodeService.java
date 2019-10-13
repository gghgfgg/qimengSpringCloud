package com.qimeng.main.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimeng.main.entity.DecviceOpenCodeLog;
import com.qimeng.main.entity.DeviceRecycleOrder;
import com.qimeng.main.entity.DeviceRecycleRealTime;
import com.qimeng.main.util.RedisUtil;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.DeviceOrderVo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年9月18日 下午10:40:01
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Transactional
@Service
public class DeviceOpenCodeService {

	@Autowired
	RedisUtil redisUtil;
	@Autowired
	DecviceOpenCodeLogService decviceOpenCodeLogService;
	@Autowired
	DeviceRecycleOrderService deviceRecycleOrderService;
	@Autowired
	DeviceRecycleRealTimeService deviceRecycleRealTimeService;
	public int createStaticOpenCode() {
		if (redisUtil.hasKey("OpenKey")) {
			redisUtil.del("OpenKey");
		}
		DeviceOrderVo deviceOrderVo = new DeviceOrderVo();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		deviceOrderVo.setOrder(uuid);
		deviceOrderVo.setQrOrder("Open$" + uuid);
		if (!redisUtil.set("OpenKey", deviceOrderVo)) {
			throw new RuntimeException("创建设备开门码失败");
		}
		DecviceOpenCodeLog decviceOpenCodeLog = new DecviceOpenCodeLog();
		decviceOpenCodeLog.setUuid(uuid);
		decviceOpenCodeLog.setCreateTime(new Date());
		return decviceOpenCodeLogService.insertDecviceOpenCodeLog(decviceOpenCodeLog);
	}

	public int createDevOrder(String machineID, DeviceOrderVo deviceOrderVo) {

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		DeviceRecycleOrder deviceRecycleOrder = new DeviceRecycleOrder();
		deviceRecycleOrder.setUuid(uuid);
		deviceRecycleOrder.setMachineId(machineID);
		deviceRecycleOrder.setOrderType(deviceOrderVo.getOrderType());
		deviceRecycleOrder.setRecycleType(deviceOrderVo.getRecycleType());
		deviceRecycleOrder.setbEnd(false);
		boolean success = false;
		if (deviceOrderVo.getOrderType() == StaticGlobal.RECORDER) {
			success = redisUtil.sethours(
					"OrderKey::Recycle::" + machineID + "-" + deviceOrderVo.getRecycleType().toString(),
					deviceRecycleOrder, 24L);
		} else {
			success = redisUtil.sethours("OrderKey::Repair::" + machineID, deviceRecycleOrder, 24L);
		}
		if (!success) {
			throw new RuntimeException("创建设备操作单失败");
		}
		success = redisUtil.sethours("OrderOpenKey::" + deviceRecycleOrder.getUuid(), deviceRecycleOrder, 24L);
		if (!success) {
			throw new RuntimeException("创建设备开门码失败");
		}
		return 1;
	}

	public DeviceOrderVo checkDevOrder(String machineID, DeviceOrderVo deviceOrderVo) {

		if (!redisUtil.hasKey("OrderOpenKey::" + deviceOrderVo.getOrder())) {
			throw new RuntimeException("找不到当前开门码");
		}
		DeviceRecycleOrder deviceRecycleOrder = (DeviceRecycleOrder) redisUtil
				.get("OrderOpenKey::" + deviceOrderVo.getOrder());
		DeviceRecycleOrder temp = null;
		if (deviceRecycleOrder.getOrderType() == StaticGlobal.RECORDER) {
			temp = (DeviceRecycleOrder) redisUtil
					.get("OrderKey::Recycle::" + machineID + "-" + deviceRecycleOrder.getRecycleType().toString());
		} else {
			temp = (DeviceRecycleOrder) redisUtil.get("OrderKey::Repair::" + machineID);
		}
		if (temp == null || !temp.getUuid().equals(deviceRecycleOrder.getUuid())) {
			throw new RuntimeException("当前开门码不匹配");
		}
		Date date=new Date();
		redisUtil.del("OrderOpenKey::" + deviceRecycleOrder.getUuid());
		if (temp.getOrderType() == StaticGlobal.RECORDER) {
			redisUtil.del("OrderKey::Recycle::" + machineID + "-" + temp.getRecycleType().toString());
			DeviceRecycleRealTime deviceRecycleRealTime=deviceRecycleRealTimeService.selectDeviceRecycleRealTime(machineID, temp.getRecycleType());
			if(deviceRecycleRealTime==null) {
				throw new RuntimeException("没有累积重量");
			}
			deviceRecycleRealTime.setEndTime(date);
			temp.setSysCount(deviceRecycleRealTime.getCount());
			deviceRecycleRealTimeService.updateDeviceRecycleRealTimeRecycle(deviceRecycleRealTime);
		} else {
			redisUtil.del("OrderKey::Repair::" + machineID); 
		}
		
		temp.setCreateTime(date);
		
		deviceRecycleOrderService.insertDeviceRecycleOrder(temp);
		
		deviceOrderVo.setRecycleType(temp.getRecycleType());
		deviceOrderVo.setOrderType(temp.getOrderType());
		deviceOrderVo.setQrOrder("Open$" + temp.getUuid());
		deviceOrderVo.setOrder(temp.getUuid());
		deviceOrderVo.setCount(0);

		return deviceOrderVo;
	}
	
	public int endDevOrder(String machineID, DeviceOrderVo deviceOrderVo) {
		DeviceRecycleOrder deviceRecycleOrder=new DeviceRecycleOrder();
		deviceRecycleOrder.setMachineId(machineID);
		deviceRecycleOrder.setUuid(deviceOrderVo.getOrder());
		deviceRecycleOrder.setEndTime(new Date());
		return deviceRecycleOrderService.updateDeviceRecycleOrderEnd(deviceRecycleOrder);
	}
	public int uploadDevOrder(String machineID, DeviceOrderVo deviceOrderVo) {
		DeviceRecycleOrder deviceRecycleOrder=new DeviceRecycleOrder();
		deviceRecycleOrder.setMachineId(machineID);
		deviceRecycleOrder.setUuid(deviceOrderVo.getOrder());
		deviceRecycleOrder.setRealCount(deviceOrderVo.getCount());
		deviceRecycleOrder.setUploadTime(new Date());
		return deviceRecycleOrderService.updateDeviceRecycleOrderCount(deviceRecycleOrder);
	}
}
