package com.qimeng.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.dao.stuInfoFeigen;

@Service
public class StuInfoService {
	@Autowired
	stuInfoFeigen stuinfo;
	
    public String index()
    {
    	return stuinfo.index();
    }
    
   
	public String getStudent(JSONObject message) 
	{
		return stuinfo.getStudent(message);
	}
	
	public String upLoadPoints(JSONObject message)
	{
		return stuinfo.upLoadPoints(message);
	}
	
	public String updateMachineID(JSONObject message)
	{
		return stuinfo.updateMachineID(message);
	}
}
