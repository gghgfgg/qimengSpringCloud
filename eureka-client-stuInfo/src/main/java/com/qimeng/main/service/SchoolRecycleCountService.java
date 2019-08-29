package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.SchoolRecycleCountDao;
import com.qimeng.main.entity.SchoolRecycleCount;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午3:44:05 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class SchoolRecycleCountService {

	private static Logger logger = Logger.getLogger(SchoolRecycleCountService.class);
	
	@Autowired
	SchoolRecycleCountDao schoolRecycleCountDao;
	
	/**
	 * 插入更新分类回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	public int insertSchoolRecycleCount(SchoolRecycleCount schoolRecycleCount) {
		try {
			return schoolRecycleCountDao.insertSchoolRecycleCount(schoolRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入学校回收数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 查找回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	List<SchoolRecycleCount> selectSchoolRecycleCountList(SchoolRecycleCount schoolRecycleCount){
		try {
			return schoolRecycleCountDao.selectSchoolRecycleCountList(schoolRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找学校回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据machineId 查找回收机
	 * @param uuid
	 * @return
	 */
	List<SchoolRecycleCount> selectSchoolRecycleCountBySchoolCode(String schoolCode){
		SchoolRecycleCount schoolRecycleCount=new SchoolRecycleCount();
		schoolRecycleCount.setSchoolCode(schoolCode);
		return selectSchoolRecycleCountList(schoolRecycleCount);
	}
	
	/**
	 * 根据回收类型  查找回收
	 * @param type
	 * @return
	 */
	List<SchoolRecycleCount> selectSchoolRecycleCountByType(byte type){
		SchoolRecycleCount schoolRecycleCount=new SchoolRecycleCount();
		schoolRecycleCount.setType(type);
		return selectSchoolRecycleCountList(schoolRecycleCount);
	}
	
	/**
	 * 根据回收类型machineId  查找回收
	 * @param type
	 * @return
	 */
	SchoolRecycleCount selectSchoolRecycleCount(String schoolCode,byte type){
		SchoolRecycleCount schoolRecycleCount=new SchoolRecycleCount();
		schoolRecycleCount.setSchoolCode(schoolCode);
		schoolRecycleCount.setType(type);
		List<SchoolRecycleCount> list=selectSchoolRecycleCountList(schoolRecycleCount);
		return list.isEmpty()?null:list.get(0);
	}
}
