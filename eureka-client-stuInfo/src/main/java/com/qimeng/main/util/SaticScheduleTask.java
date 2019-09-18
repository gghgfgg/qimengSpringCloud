package com.qimeng.main.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.qimeng.main.service.DeviceOpenCodeService;
import com.qimeng.main.service.SchoolAutoCountRecycleService;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月6日 下午9:16:23 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务
   // @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
	@Autowired
	SchoolAutoCountRecycleService schoolAutoCountRecycleService;
	@Autowired
	DeviceOpenCodeService deviceOpenCodeService;
	
	@Scheduled(cron = "0 5 0 * * ? ")
   // @Scheduled(cron = "0 23 21 * * ? ")
    private void configureDateTasks() {
    	schoolAutoCountRecycleService.SchoolAutoCountRecycleByDay();
    }
	
	@Scheduled(cron = "0 10 0 1 * ? ")
	 private void configureMonthTasks() {
	    	schoolAutoCountRecycleService.SchoolAutoCountRecycleByMonth();
	 }
	@Scheduled(cron = "0 20 0 1 1 ? ")
	 private void configureYearTasks() {
	    	schoolAutoCountRecycleService.SchoolAutoCountRecycleByYear();
	 }
	
	@Scheduled(cron = "0 0 23 * * 7 ")
	//@Scheduled(cron = "0 49 1 * * *")
	private void createOpenCode() {
		deviceOpenCodeService.createStaticOpenCode();
	}
}
