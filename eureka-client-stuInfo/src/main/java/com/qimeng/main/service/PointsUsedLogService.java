package com.qimeng.main.service;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午4:01:24 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.PointsUsedLogDao;
import com.qimeng.main.entity.PointsUsedLog;

@Service
public class PointsUsedLogService {
	private static Logger logger = Logger.getLogger(PointsUsedLogService.class);
	
	@Autowired
	PointsUsedLogDao pointsUsedLogDao;
	
	/**
	 * @param pointsUsedLog
	 * @return
	 */
	public int insertPointsUsedLog(PointsUsedLog pointsUsedLog) {
		try {
			return pointsUsedLogDao.insertPointsUsedLog(pointsUsedLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入积分使用日志数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param pointsUsedLog
	 * @return
	 */
	public List<PointsUsedLog> selectPointsUsedLogList(PointsUsedLog pointsUsedLog){
		try {
			return pointsUsedLogDao.selectPointsUsedLogList(pointsUsedLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找积分使用日志数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
}

