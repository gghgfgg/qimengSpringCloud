package com.qimeng.main.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-client-stuInfo")
public interface stuInfoFeigen {
	@RequestMapping("/index")
	public String index();
}
