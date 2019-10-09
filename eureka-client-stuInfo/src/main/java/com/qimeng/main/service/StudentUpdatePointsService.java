package com.qimeng.main.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimeng.main.entity.DeviceManagement;
import com.qimeng.main.entity.DeviceRecycleCount;
import com.qimeng.main.entity.DeviceRecycleLog;
import com.qimeng.main.entity.DeviceRecycleRealTime;
import com.qimeng.main.entity.ExcelRecycle;
import com.qimeng.main.entity.PointsManageLog;
import com.qimeng.main.entity.PointsUsedLog;
import com.qimeng.main.entity.RecycleType;
import com.qimeng.main.entity.SchoolRecycleCount;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentRecycleCount;
import com.qimeng.main.util.StaticGlobal;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 上午9:56:44 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Transactional
@Service
public class StudentUpdatePointsService {
	
	@Autowired
	StudentDataService studentDataService;
	@Autowired
	RecycleTypeService recycleTypeService;
	@Autowired
	DeviceManagementService deviceManagementService;
	@Autowired
	DeviceRecycleLogService deviceRecycleLogService;
	@Autowired
	StudentRecycleCountService studentRecycleCountService;
	@Autowired
	DeviceRecycleCountService deviceRecycleCountService;
	@Autowired
	PointsUsedLogService pointsUsedLogService;
	@Autowired
	PointsManageLogService pointsManageLogService;
	@Autowired
	SchoolRecycleCountService schoolRecycleCountService;
	@Autowired
	StudentRankService studentRankService;
	@Autowired
	DeviceRecycleRealTimeService deviceRecycleRealTimeService;
	
	public int updateStudentPoints(StudentData studentData,int wasteType,int uint,String machineId,Date date) {
		try {
			RecycleType recycleType=recycleTypeService.selectRecycleTypeByType((byte) wasteType);
			if(recycleType==null) {
				throw new RuntimeException("找不到当前回收类型");
			}
			DeviceManagement deviceManagement=deviceManagementService.selectDeviceManagementListByMachineId(machineId,StaticGlobal.ACTIVE);
			if(deviceManagement==null) {
				throw new RuntimeException("找不到当前回收机器");
			}
			StudentRecycleCount studentRecycleCount=studentRecycleCountService.selectStudentRecycleCount(studentData.getUuid(), (byte) wasteType);
			if(studentRecycleCount==null) {
				studentRecycleCount=new StudentRecycleCount();
				studentRecycleCount.setActivityCount(0);
				studentRecycleCount.setCount(0);
				studentRecycleCount.setPoints(0);
				studentRecycleCount.setRemainder(0);
				studentRecycleCount.setType((byte) wasteType);
				studentRecycleCount.setUuid(studentData.getUuid());
				studentRecycleCount.setCreateTime(date);
				studentRecycleCount.setUpdateTime(date);
			}
			DeviceRecycleCount deviceRecycleCount=deviceRecycleCountService.selectDeviceRecycleCount(deviceManagement.getMachineId(), (byte) wasteType);
			if(deviceRecycleCount==null) {
				deviceRecycleCount=new DeviceRecycleCount();
				deviceRecycleCount.setActivityCount(0);
				deviceRecycleCount.setCount(0);
				deviceRecycleCount.setPoints(0);
				deviceRecycleCount.setRemainder(0);
				deviceRecycleCount.setType((byte) wasteType);
				deviceRecycleCount.setMachineId(deviceManagement.getMachineId());
				deviceRecycleCount.setCreateTime(date);
				deviceRecycleCount.setUpdateTime(date);
			}
			SchoolRecycleCount schoolRecycleCount=schoolRecycleCountService.selectSchoolRecycleCount(deviceManagement.getSchoolCode(),(byte) wasteType);
			if(schoolRecycleCount==null) {
				schoolRecycleCount=new SchoolRecycleCount();
				schoolRecycleCount.setActivityCount(0);
				schoolRecycleCount.setCount(0);
				schoolRecycleCount.setPoints(0);
				schoolRecycleCount.setRemainder(0);
				schoolRecycleCount.setType((byte) wasteType);
				schoolRecycleCount.setSchoolCode(deviceManagement.getSchoolCode());
				schoolRecycleCount.setCreateTime(date);
				schoolRecycleCount.setUpdateTime(date);
			}
			DeviceRecycleRealTime deviceRecycleRealTime=deviceRecycleRealTimeService.selectDeviceRecycleRealTime(deviceManagement.getMachineId(), (byte) wasteType);
			if(deviceRecycleRealTime==null) {
				deviceRecycleRealTime=new DeviceRecycleRealTime();
				deviceRecycleRealTime.setbRecycle(false);
				deviceRecycleRealTime.setCount(0);
				deviceRecycleRealTime.setCreateTime(date);
				deviceRecycleRealTime.setMachineId(deviceManagement.getMachineId());
				deviceRecycleRealTime.setType((byte) wasteType);
				deviceRecycleRealTimeService.insertDeviceRecycleRealTime(deviceRecycleRealTime);
			}
			
				
			DeviceRecycleLog deviceRecycleLog=new DeviceRecycleLog();
			deviceRecycleLog.setMachineId(deviceManagement.getMachineId());
			deviceRecycleLog.setSchoolidOfDevice(deviceManagement.getSchoolCode());
			deviceRecycleLog.setUuid(studentData.getUuid());
			deviceRecycleLog.setSchoolidOfStudent(studentData.getSchoolCode());
			deviceRecycleLog.setRecycleType((byte) wasteType);
			deviceRecycleLog.setIdentityType(studentData.getType());
			deviceRecycleLog.setCreateTime(date);
			deviceRecycleLog.setFactor(recycleType.getFactor());
			deviceRecycleLog.setCount(uint);
			deviceRecycleLog.setLastRemainder(studentRecycleCount.getRemainder());
			int points=(studentRecycleCount.getRemainder()+uint)/recycleType.getFactor();
			int remainder=(studentRecycleCount.getRemainder()+uint)%recycleType.getFactor();
			deviceRecycleLog.setPoints(points);
			deviceRecycleLog.setRemainder(remainder);
			deviceRecycleLogService.insertDeviceRecycleLog(deviceRecycleLog);
			
			if(studentData.getActive()==0) {
				studentData.setActive((byte) 1);
				studentData.setFirstTime(date);
				studentData.setActivityCount(studentData.getActivityCount()+1);
				studentData.setTotalPoints(studentData.getTotalPoints()+points);
				studentData.setLastTime(date);
				studentDataService.setStudentActiveByUuid(studentData);
			}else {
				studentData.setActivityCount(studentData.getActivityCount()+1);
				studentData.setTotalPoints(studentData.getTotalPoints()+points);
				studentData.setLastTime(date);
				studentDataService.updateStudentTotalPoints(studentData);
			}
			studentRankService.addStudentPoints(studentData); 
			
			studentRecycleCount.setActivityCount(studentRecycleCount.getActivityCount()+1);
			studentRecycleCount.setCount(studentRecycleCount.getCount()+uint);
			studentRecycleCount.setPoints(studentRecycleCount.getPoints()+points);
			studentRecycleCount.setRemainder(remainder);
			studentRecycleCount.setUpdateTime(date);
			studentRecycleCountService.insertStudentRecycleCount(studentRecycleCount);
			
			deviceRecycleCount.setActivityCount(deviceRecycleCount.getActivityCount()+1);
			deviceRecycleCount.setCount(deviceRecycleCount.getCount()+uint);
			points=(deviceRecycleCount.getRemainder()+uint)/recycleType.getFactor();
			remainder=(deviceRecycleCount.getRemainder()+uint)%recycleType.getFactor();
			deviceRecycleCount.setPoints(deviceRecycleCount.getPoints()+points);
			deviceRecycleCount.setRemainder(remainder);
			deviceRecycleCount.setUpdateTime(date);
			deviceRecycleCountService.insertDeviceRecycleCount(deviceRecycleCount);
			
			schoolRecycleCount.setActivityCount(schoolRecycleCount.getActivityCount()+1);
			schoolRecycleCount.setCount(schoolRecycleCount.getCount()+uint);
			points=(schoolRecycleCount.getRemainder()+uint)/recycleType.getFactor();
			remainder=(schoolRecycleCount.getRemainder()+uint)%recycleType.getFactor();
			schoolRecycleCount.setPoints(schoolRecycleCount.getPoints()+points);
			schoolRecycleCount.setRemainder(remainder);
			schoolRecycleCount.setUpdateTime(date);
			schoolRecycleCountService.insertSchoolRecycleCount(schoolRecycleCount);
					
			deviceRecycleRealTime.setCount(deviceRecycleRealTime.getCount()+uint);
			deviceRecycleRealTimeService.updateDeviceRecycleRealTimeCount(deviceRecycleRealTime);
			
			studentRankService.addSchoolPoints(schoolRecycleCount,points);
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public int updateStudentPoints(StudentData studentData,int wasteType,int uint,String machineId) {
		return updateStudentPoints(studentData,wasteType,uint,machineId,new Date());
	}
	
	public int usedStudentPoints(StudentData studentData,int usedPoints,String mark,String appId,Date date) {
		try {
			studentData.setUsedPoints(studentData.getUsedPoints()+usedPoints);
			studentDataService.updateStudentUsedPoints(studentData);
			PointsUsedLog pointsUsedLog=new PointsUsedLog();
			pointsUsedLog.setAppId(appId);
			pointsUsedLog.setUuid(studentData.getUuid());
			pointsUsedLog.setName(studentData.getName());
			pointsUsedLog.setPoints(usedPoints);
			pointsUsedLog.setMark(mark);
			pointsUsedLog.setCreateTime(date);
			pointsUsedLogService.insertPointsUsedLog(pointsUsedLog);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public int usedStudentPoints(StudentData studentData,int usedPoints,String mark,String appId) {
		return usedStudentPoints(studentData,usedPoints,mark,appId,new Date());
	}
	public int deductStudentPoints(StudentData studentData,int deductPoints,String mark,String operator,String appId) {
		try {
			Date date=new Date();
			studentData.setDeductPoints(studentData.getDeductPoints()+deductPoints);
			studentDataService.updateStudentDeductPoints(studentData);
			PointsManageLog pointsManageLog=new PointsManageLog();
			pointsManageLog.setAppId(appId);
			pointsManageLog.setUuid(studentData.getUuid());
			pointsManageLog.setName(studentData.getName());
			pointsManageLog.setPoints(deductPoints);
			pointsManageLog.setOperator(operator);
			pointsManageLog.setMark(mark);
			pointsManageLog.setCreateTime(date);
			pointsManageLogService.insertPointsManageLog(pointsManageLog);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int updateStudentPointsByExcel(List<ExcelRecycle> list) throws ParseException {
		// TODO Auto-generated method stub
		int i=0;
		for (ExcelRecycle excelRecycle : list) {
			i++;
			StudentData studentData = studentDataService.selectStudentDataByQRID(excelRecycle.getQrid());
			if (studentData==null) {
				System.out.println("{第" + String.valueOf(i + 1) + "行,qrid"+excelRecycle.getQrid()+"},");
				continue;
			}
			updateStudentPoints(studentData,excelRecycle.getRecycleType(),excelRecycle.getCount(),excelRecycle.getMachineId(),excelRecycle.getCreateTime());
		}
		return 1;
	}
	public int updateStudentUsedPointsByExcel(List<ExcelRecycle> list,String mark,String appId) {
		// TODO Auto-generated method stub
		int i=0;
		for (ExcelRecycle excelRecycle : list) {
			i++;
			StudentData studentData = studentDataService.selectStudentDataByQRID(excelRecycle.getQrid());
			if (studentData==null) {
				System.out.println("{第" + String.valueOf(i + 1) + "行,qrid"+excelRecycle.getQrid()+"},");
				continue;
			}
			usedStudentPoints(studentData,excelRecycle.getPoints(),mark,appId,excelRecycle.getCreateTime());
		}
		return 1;
	}
}

