package com.qimeng.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author 陈泽键
 * <P>学生信息系统
 */

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientStuInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientStuInfoApplication.class, args);
	}

}
