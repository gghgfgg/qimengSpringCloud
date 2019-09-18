package com.qimeng.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.service.SchoolInformService;
import com.qimeng.main.service.StudentRankService;

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
	
	@Autowired
	StudentRankService studentRankService;
	@Autowired
	SchoolInformService schoolInformService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("系统启动自动执行");
		
		studentRankService.addListPoints();
		
		studentRankService.addSchoolListPoints();
		
		List<SchoolInform> schoolInform=schoolInformService.selectSchoolInformList(new SchoolInform());
		for (SchoolInform item : schoolInform) {
			studentRankService.addListSchoolCount(item.getSchoolCode());
		}
	}

}

