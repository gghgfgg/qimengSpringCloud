package com.qimeng.main.oldStuService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.vo.OldResponseJson;


@Service
public class OldService {

	public static final String BASE_URL = "http://admin.qimenghb.com/portal/inf/";
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	  
	public OldResponseJson getStudent(String stucode) {
		 HashMap<String, String> map = new HashMap<>();
	     map.put("code",stucode);
	     map.put("class","1");
	     String json = restTemplate.getForEntity(BASE_URL+"getUser.jsp?code={code}&class={class}", String.class, map).getBody();
		 System.out.println(json);
		 OldResponseJson messJson=JSONObject.parseObject(json,OldResponseJson.class);
		 return messJson;
	}
}
