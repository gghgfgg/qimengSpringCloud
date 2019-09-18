package com.qimeng.main.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.DecviceOpenCodeLogDao;
import com.qimeng.main.entity.DecviceOpenCodeLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月18日 下午8:49:26 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class DecviceOpenCodeLogService {
	private static Logger logger = Logger.getLogger(DecviceOpenCodeLogService.class);
	@Autowired
	DecviceOpenCodeLogDao decviceOpenCodeLogDao;
	public int  insertDecviceOpenCodeLog(DecviceOpenCodeLog decviceOpenCodeLog) {
		try {
			return decviceOpenCodeLogDao.insertDecviceOpenCodeLog(decviceOpenCodeLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置新的开门码异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
}

