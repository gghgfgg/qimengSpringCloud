package com.qimeng.main;


import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author 陈泽键
 * <P>学生信息系统
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class EurekaClientStuInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientStuInfoApplication.class, args);
		System.out.println(TimeZone.getDefault()); 
		//先执行static代码块，再执行该方法
		//是否输出值为null的字段,默认为false
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteMapNullValue.getMask();
		//数值字段如果为null,输出为0,而非null
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullNumberAsZero.getMask();
		//List字段如果为null,输出为[],而非null
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullListAsEmpty.getMask();
		//字符类型字段如果为null,输出为 "",而非null
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullStringAsEmpty.getMask();
	}

}
