package com.qimeng.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.dao.UserManageFeigen;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月2日 下午4:47:24 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class UserManageService {
	@Autowired
	UserManageFeigen userManageFeigen;
	public String getLogin(JSONObject message) {
		return userManageFeigen.getLogin(message);
	}
	
	public String getPhone(JSONObject message) {
		return userManageFeigen.getPhone(message);
	}
	
	public String bindStudent(JSONObject message) {
		return userManageFeigen.bindStudent(message);
	}
}

