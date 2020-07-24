package com.dataManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.pool.DruidDataSource;
import com.dataManage.data1.service.Data1Service;
import com.dataManage.service.DataManageService;
import com.dataManage.util.GsonUtil;
import com.google.gson.Gson;

@RestController
public class DataManageController {
	@Autowired
	private DataManageService manageService;
	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping("/add")
	public String add() {
		return manageService.add();
	}

	@RequestMapping("/extractData")
	public String queryDataList() {
		return manageService.extractData();
	}

	@RequestMapping("/extractHttpData")
	public String extractHttpData() {
		return manageService.extractHttpData();
	}

	/**
	 * 历史数据查询
	 * 
	 * @param json
	 *            conf_id,start_date,end_date
	 * @return 
	 */
	@RequestMapping("/getDataHistory")
	public Object getDataHistory(@RequestBody String json) {
		
		DruidDataSource d1=applicationContext.getBean("data1Source",DruidDataSource.class);  
		System.out.println(d1.getName());
		DruidDataSource d2=applicationContext.getBean("data2Source",DruidDataSource.class);  
		System.out.println(d2.getName());
		Map<String, String> map = GsonUtil.GsonToMaps(json);
		List<Map<String, Object>> list = null;
		String code = "200";
		String message = "成功";

		if (map == null || !map.containsKey("confId") || !map.containsKey("startDate")) {
			code = "100";
			message = "参数不正确";
		} else {
			list = manageService.getDataHistory(map);
		}
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("code", code);
		reMap.put("message", message);
		reMap.put("data", list);
		return reMap;
	}

}
