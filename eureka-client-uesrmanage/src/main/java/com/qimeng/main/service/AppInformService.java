package com.qimeng.main.service;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 上午11:18:36 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.AppInformDao;
import com.qimeng.main.entity.AppInform;
@Service
public class AppInformService {
	private static Logger logger = Logger.getLogger(AppInformService.class);
	@Autowired
	AppInformDao appInformDao;
	
	public AppInform selectAppInform(String apptype) {
		try {
			return appInformDao.selectAppInform(apptype);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取app信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
}

