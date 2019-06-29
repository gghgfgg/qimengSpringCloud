package com.qimeng.main.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient("eureka-client-shop")
public interface ShopFeigenInerface {
	@RequestMapping("/index2")
	public String index();
}
