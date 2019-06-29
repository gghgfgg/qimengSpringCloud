package com.qimeng.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.ShopFeigenInerface;

@Service
public class ShopService {
    @Autowired
	ShopFeigenInerface shopFeigenInerface;
    
    public String index() {
    	return shopFeigenInerface.index();
	}
}
