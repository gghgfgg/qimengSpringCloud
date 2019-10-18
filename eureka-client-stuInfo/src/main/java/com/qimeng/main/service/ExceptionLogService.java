package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.ExceptionLogDao;
import com.qimeng.main.entity.ExceptionLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月18日 下午7:05:07 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class ExceptionLogService {
	private static Logger logger = Logger.getLogger(ExceptionLogService.class); 
	@Autowired
	ExceptionLogDao exceptionLogDao;
	
	/**
	 * @param exceptionLog
	 * @return
	 */
	public int insertExceptionLog(ExceptionLog exceptionLog) {
		try {
			return exceptionLogDao.insertExceptionLog(exceptionLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入学生异常回收日志数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<ExceptionLog> selectExceptionLogList(ExceptionLog exceptionLog){
		try {
			return exceptionLogDao.selectExceptionLogList(exceptionLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询学生异常回收日志数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<ExceptionLog> selectExceptionLogListByUuid(String uuid,Byte type){
		ExceptionLog exceptionLog = new ExceptionLog();
		exceptionLog.setUuid(uuid);
		exceptionLog.setType(type);
		List<ExceptionLog> list = selectExceptionLogList(exceptionLog);
		return list;
	}
}

