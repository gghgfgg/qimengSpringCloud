package com.qimeng.main.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月2日 下午4:36:17 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@FeignClient("eureka-client-uesrmanage")
public interface UserManageFeigen {
	@RequestMapping("/wechat/login")
	public String getLogin(@RequestBody JSONObject message);
	
	@RequestMapping("/wechat/phone")
	public String getPhone(@RequestBody JSONObject message);
	
	@RequestMapping("/wechat/bindstu")
	public String bindStudent(@RequestBody JSONObject message);
}

