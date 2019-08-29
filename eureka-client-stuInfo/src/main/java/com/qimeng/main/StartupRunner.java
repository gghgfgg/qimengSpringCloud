package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月27日 下午5:06:12 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Component
public class StartupRunner implements CommandLineRunner {
	private static Logger logger = Logger.getLogger(StartupRunner.class);
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("系统启动自动执行");
		
		
	}

}

