package com.qimeng.main.service;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午5:24:32 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.PointsManageLogDao;
import com.qimeng.main.entity.PointsManageLog;



@Service
public class PointsManageLogService {

private static Logger logger = Logger.getLogger(PointsManageLogService.class);
	
	@Autowired
	PointsManageLogDao pointsManageLogDao;
	
	/**
	 * @param pointsUsedLog
	 * @return
	 */
	public int insertPointsManageLog(PointsManageLog pointsManageLog) {
		try {
			return pointsManageLogDao.insertPointsManageLog(pointsManageLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入积分管理日志数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param pointsUsedLog
	 * @return
	 */
	public List<PointsManageLog> selectPointsManageLogList(PointsManageLog pointsManageLog){
		try {
			return pointsManageLogDao.selectPointsManageLogList(pointsManageLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找积分管理日志数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
}

