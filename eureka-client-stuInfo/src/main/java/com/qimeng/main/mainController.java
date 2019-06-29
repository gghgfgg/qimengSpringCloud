package com.qimeng.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
