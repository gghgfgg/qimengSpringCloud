package com.qimeng.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 陈泽键
 * <P>统一Api出口
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients()
@ComponentScan
public class EurekaClientApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApiApplication.class, args);
	}

}
