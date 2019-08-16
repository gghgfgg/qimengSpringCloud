package com.qimeng.main.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.entity.DeviceRecycleLog;
import com.qimeng.main.entity.RecycleType;
import com.qimeng.main.entity.SchoolAutoCount;
import com.qimeng.main.entity.SchoolInform;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月5日 下午11:34:56
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class SchoolAutoCountRecycleService {
	
	private static Logger logger = Logger.getLogger(SchoolAutoCountRecycleService.class);
	public static final byte Day=1;
	public static final byte Mouth=2;
	public static final byte Year=3;
	@Autowired
	SchoolInformService schoolInformService;
	@Autowired
	RecycleTypeService recycleTypeService;
	@Autowired
	DeviceRecycleLogService deviceRecycleLogService;
	@Autowired
	SchoolAutoCountService schoolAutoCountService;
	
	
	public int SchoolAutoCountRecycleByDay() {
		try {

			List<RecycleType> typelist = recycleTypeService.selectRecycleTypeList();
			List<SchoolInform> list = schoolInformService.selectSchoolInformList(true);
			Date date = new Date();
			for (SchoolInform schoolInform : list) {
				for (RecycleType recycleType : typelist) {
					List<DeviceRecycleLog> logList = deviceRecycleLogService
							.selectDeviceRecycleLogBySchoolDay(schoolInform.getSchoolCode(), recycleType.getType());
					int logcount=0;
					for (DeviceRecycleLog deviceRecycleLog : logList) {
						logcount+=deviceRecycleLog.getCount();
					}
					SchoolAutoCount schoolAutoCount=new SchoolAutoCount();
					schoolAutoCount.setActivityCount(logList.size());
					schoolAutoCount.setCount(logcount);
					schoolAutoCount.setCreateTime(date);
					schoolAutoCount.setSchoolCode(schoolInform.getSchoolCode());
					schoolAutoCount.setType(recycleType.getType());
					int points=logcount/recycleType.getFactor();
					int remainder=logcount%recycleType.getFactor();
					schoolAutoCount.setPoints(points);
					schoolAutoCount.setRemainder(remainder);
					schoolAutoCount.setCountType(Day);
					schoolAutoCountService.insertSchoolAutoCount(schoolAutoCount);
				}
			}
			logger.info("自动生成每日数据报表");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public int SchoolAutoCountRecycleByMonth() {
		try {

			List<RecycleType> typelist = recycleTypeService.selectRecycleTypeList();
			List<SchoolInform> list = schoolInformService.selectSchoolInformList(true);
			Date date = new Date();
			for (SchoolInform schoolInform : list) {
				for (RecycleType recycleType : typelist) {
					List<SchoolAutoCount> logList = schoolAutoCountService.selectSchoolAutoCountForDay(schoolInform.getSchoolCode(), recycleType.getType());
					int logcount=0;
					int logactive=0;
					for (SchoolAutoCount schoolAutoCount : logList) {
						logcount+=schoolAutoCount.getCount();
						logactive+=schoolAutoCount.getActivityCount();
					}
					SchoolAutoCount schoolAutoCount=new SchoolAutoCount();
					schoolAutoCount.setActivityCount(logactive);
					schoolAutoCount.setCount(logcount);
					schoolAutoCount.setCreateTime(date);
					schoolAutoCount.setSchoolCode(schoolInform.getSchoolCode());
					schoolAutoCount.setType(recycleType.getType());
					int points=logcount/recycleType.getFactor();
					int remainder=logcount%recycleType.getFactor();
					schoolAutoCount.setPoints(points);
					schoolAutoCount.setRemainder(remainder);
					schoolAutoCount.setCountType(Mouth);
					schoolAutoCountService.insertSchoolAutoCount(schoolAutoCount);
				}
			}
			logger.info("自动生成每月数据报表");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public int SchoolAutoCountRecycleByYear() {
		try {

			List<RecycleType> typelist = recycleTypeService.selectRecycleTypeList();
			List<SchoolInform> list = schoolInformService.selectSchoolInformList(true);
			Date date = new Date();
			for (SchoolInform schoolInform : list) {
				for (RecycleType recycleType : typelist) {
					List<SchoolAutoCount> logList = schoolAutoCountService.selectSchoolAutoCountForMonth(schoolInform.getSchoolCode(), recycleType.getType());
					int logcount=0;
					int logactive=0;
					for (SchoolAutoCount schoolAutoCount : logList) {
						logcount+=schoolAutoCount.getCount();
						logactive+=schoolAutoCount.getActivityCount();
					}
					SchoolAutoCount schoolAutoCount=new SchoolAutoCount();
					schoolAutoCount.setActivityCount(logactive);
					schoolAutoCount.setCount(logcount);
					schoolAutoCount.setCreateTime(date);
					schoolAutoCount.setSchoolCode(schoolInform.getSchoolCode());
					schoolAutoCount.setType(recycleType.getType());
					int points=logcount/recycleType.getFactor();
					int remainder=logcount%recycleType.getFactor();
					schoolAutoCount.setPoints(points);
					schoolAutoCount.setRemainder(remainder);
					schoolAutoCount.setCountType(Year);
					schoolAutoCountService.insertSchoolAutoCount(schoolAutoCount);
				}
			}
			logger.info("自动生成每年数据报表");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
}
