package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.MainToStudentDao;
import com.qimeng.main.dao.UserMainDao;
import com.qimeng.main.entity.MainToStudent;
import com.qimeng.main.entity.UserMain;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午10:03:06 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class MainToStudentService {

	private static Logger logger = Logger.getLogger(MainToStudentService.class);
	@Autowired
	MainToStudentDao mainToStudentDao;
	
	public int insertMainToStudent(MainToStudent mainToStudent) {
		try {
			return mainToStudentDao.insertMainToStudent(mainToStudent);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int updateMainToStudent(MainToStudent mainToStudent) {
		try {
			return mainToStudentDao.updateMainToStudent(mainToStudent);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public MainToStudent selectMainToStudent(MainToStudent mainToStudent) {
		try {
			return mainToStudentDao.selectMainToStudent(mainToStudent);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
}

