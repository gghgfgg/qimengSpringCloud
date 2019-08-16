package com.qimeng.main.service;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午4:58:15 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.SchoolAutoCountDao;
import com.qimeng.main.entity.SchoolAutoCount;

@Service
public class SchoolAutoCountService {

	private static Logger logger = Logger.getLogger(SchoolAutoCountService.class);
	
	@Autowired
	SchoolAutoCountDao schoolAutoCountDao;
	
	/**
	 * 插入更新分类回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	public int insertSchoolAutoCount(SchoolAutoCount schoolAutoCount) {
		try {
			return schoolAutoCountDao.insertSchoolAutoCount(schoolAutoCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入学校自动统计回收数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 查找回收数据
	 * @param studentAutoCount
	 * @return
	 */
	public List<SchoolAutoCount> selectSchoolAutoCountList(SchoolAutoCount schoolAutoCount){
		try {
			return schoolAutoCountDao.selectSchoolAutoCountList(schoolAutoCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找学校自动统计回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查找回收数据
	 * @param studentAutoCount
	 * @return
	 */
	public List<SchoolAutoCount> selectSchoolAutoCountForDay(String schoolCode,byte type){
		try {
			return schoolAutoCountDao.selectSchoolAutoCountForDay(schoolCode,type);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找学校自动统计日回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找回收数据
	 * @param studentAutoCount
	 * @return
	 */
	public List<SchoolAutoCount> selectSchoolAutoCountForMonth(String schoolCode,byte type){
		try {
			return schoolAutoCountDao.selectSchoolAutoCountForMonth(schoolCode,type);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找学校自动统计月回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
}

