package com.qimeng.main.wechat;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.entity.AppInform;
import com.qimeng.main.service.AppInformService;
import com.qimeng.main.util.EncryptUtil;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 上午11:27:14 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class WeChatService {
	
	private static Logger logger = Logger.getLogger(WeChatService.class);
	
	public static final String BASE_URL = "https://api.weixin.qq.com/";
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AppInformService appInformService;
	
	public String getCode2Session(String code) {
		 Map<String, String> map = new HashMap<>();
	     map.put("JSCODE",code);
	     AppInform appInform= appInformService.selectAppInform("wechat");
	     map.put("APPID",appInform.getAppid());
	     map.put("SECRET",appInform.getSecret());
	     String json = restTemplate.getForEntity(BASE_URL+"sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code", String.class, map).getBody();
	     logger.debug(json);
	     JSONObject jsonObject=JSONObject.parseObject(json);
	     
	     if(jsonObject.getIntValue("errcode")!=0)
	     {
	    	 throw new RuntimeException(jsonObject.getString("errmsg"));
	     }
		 return json;
	}
	
	public String getPhoneNumber(String encryptedData,String sessionKey,String vi) throws Exception{
		EncryptUtil encryptUtil=new EncryptUtil();
		String decode=encryptUtil.decodeWechat(sessionKey,vi,encryptedData);
		JSONObject jsonObject=JSONObject.parseObject(decode);
		return jsonObject.getString("phoneNumber");
	}

}

