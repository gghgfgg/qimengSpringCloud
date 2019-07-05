package com.qimeng.main.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

@FeignClient("eureka-client-stuInfo")
public interface stuInfoFeigen {
	@RequestMapping("/index")
	public String index();
	
	@RequestMapping("/getstudent")
	public String getStudent(@RequestBody JSONObject message);
	
	@RequestMapping("/uploadpoints")
	public String upLoadPoints(@RequestBody JSONObject message);
	
	@RequestMapping("/updatemachineid")
	public String updateMachineID(@RequestBody JSONObject message);
}
