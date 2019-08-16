package com.qimeng.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.GlobalDateDao;
import com.qimeng.main.entity.GlobalDate;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月15日 下午3:43:36 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class GlobalDateService {
	private static Logger logger = Logger.getLogger(GlobalDateService.class);
	public static Map<String,String> global= new  HashMap<String,String>();
	@Autowired
	GlobalDateDao globalDateDao;
	
	List<GlobalDate> selectGlobalDateList(){
		try {
			return globalDateDao.selectGlobalDateList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取全局变量异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	String getGlobalKeyString(String key) {
		if(global.get(key)==null) {
			List<GlobalDate> list=selectGlobalDateList();
			for (GlobalDate globalDate : list) {
				global.put(globalDate.getGlobalKey(), globalDate.getGlobalValue());
			}
		}
		logger.debug(global.get(key));
		return global.get(key);
	}
	
}

