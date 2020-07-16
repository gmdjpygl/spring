package com.data.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.data.service.DataManageService;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
	@Autowired
	private DataManageService dataManageService;
	
    //3.添加定时任务
    //@Scheduled(cron = "${configtask.cron}")
    private void configureTasks() {
    	dataManageService.extractHttpData();
    }
}
