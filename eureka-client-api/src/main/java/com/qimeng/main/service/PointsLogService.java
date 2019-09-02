package com.qimeng.main.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.PointsLogDao;
import com.qimeng.main.entity.PointsLog;

@Service
public class PointsLogService {
	@Autowired
	PointsLogDao PointsLogDao;
	public int insertPointsLog(PointsLog pointsLog)
	{
		return PointsLogDao.insertPointsLog(pointsLog);
	}
	
	
}
