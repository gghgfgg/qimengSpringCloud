package com.qimeng.main.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.UploadlogDao;
import com.qimeng.main.entity.Uploadlog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月16日 下午12:38:13 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class UploadlogService {
	private static Logger logger = Logger.getLogger(UploadlogService.class); 
	
	@Autowired
	UploadlogDao uploadlogDao;
	
	public int  insertUploadlog(Uploadlog uploadlog) {
		try {
			return uploadlogDao.insertUploadlog(uploadlog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("上传学生信息文件异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
}

