package com.data.task;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import com.data.util.DateUtil;

@Component  //实现SchedulingConfigurer并重写configureTasks方法
@EnableScheduling
public class OrderJobThread implements SchedulingConfigurer {
	
    private String cron = "0/5 * * * * ?"; //调用set方法可动态设置时间规则
     
    public String getCron() {
        return cron;
    }
 
    public void setCron(String cron) {
        this.cron = cron;
    }
 
    private String name = "测试"; //调用set方法可以动态设置日志名
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(new Runnable() {
            @Override
            public void run() {
            	System.out.println("-------------"+DateUtil.format(new Date())+"-------------");
				/*
				 * try { Thread.sleep(1000*20L); } catch (InterruptedException
				 * e) { // TODO Auto-generated catch block e.printStackTrace();
				 * }
				 */
            	
            }
        }, cron);  //加入时间
    }
     
     
}