package com.qimeng.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.UsePointsLogDao;
import com.qimeng.main.entity.UsePointsLog;

@Service
public class UsePointsLogService {
	@Autowired
	UsePointsLogDao usePointsLogDao;
	
	public int insertPointsLog(UsePointsLog usepointsLog)
	{
		return usePointsLogDao.insertPointsLog(usepointsLog);
	}

}
