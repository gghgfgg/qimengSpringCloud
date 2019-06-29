package com.qimeng.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qimeng.main.service.ShopService;
import com.qimeng.main.service.StuInfoService;


@RestController
public class mainController {
	
	@Autowired
	StuInfoService stuService;
	@Autowired
	ShopService shopService;
	
	@RequestMapping("/")
	public String index() {
		return stuService.index();
	}
	
	@RequestMapping("/index")
	public String index2() {
		return shopService.index();
	}
}