package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.UserMainDao;
import com.qimeng.main.entity.UserMain;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午3:55:44 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class UserMainService {
	private static Logger logger = Logger.getLogger(UserMainService.class);
	@Autowired
	UserMainDao userMainDao;
	
	public int insertUserMain(UserMain userMain) {
		try {
			return userMainDao.insertUserMain(userMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int updateUserMain(UserMain userMain) {
		try {
			return userMainDao.updateUserMain(userMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<UserMain> selectUserMainList(UserMain userMain) {
		try {
			return userMainDao.selectUserMainList(userMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public UserMain selectUserMainByPhone(String phone) {
		UserMain userMain=new UserMain();
		userMain.setPhone(phone);
		List<UserMain> list=selectUserMainList(userMain);
		return list.isEmpty()?null:list.get(0);
	}
	
}

