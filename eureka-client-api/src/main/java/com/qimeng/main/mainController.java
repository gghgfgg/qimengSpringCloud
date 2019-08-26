package com.qimeng.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.service.ShopService;
import com.qimeng.main.service.StuInfoService;


@RestController
@RequestMapping("/stuinfo")
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
	
	@RequestMapping("/getstudent")
	public String getStudent(@RequestBody JSONObject message)
	{
		return stuService.getStudent(message);
	}
	
	@RequestMapping("/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message)
	{
		return stuService.upLoadPoints(message);
	}
	
	@RequestMapping("/updatemachineid")
	public String updateMachineID(@RequestBody JSONObject message)
	{
		return stuService.upLoadPoints(message);
	}
	
	
}