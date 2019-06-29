package com.qimeng.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.stuInfoFeigen;

@Service
public class StuInfoService {
	@Autowired
	stuInfoFeigen stuinfo;
	
    public String index()
    {
    	return stuinfo.index();
    }
}
