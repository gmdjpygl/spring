package com.dataManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataManage.data1.service.Data1Service;
import com.dataManage.service.DataManageService;
import com.dataManage.util.GsonUtil;
import com.google.gson.Gson;

@RestController
public class DataManageController {
	@Autowired
	private Data1Service data1Service;
	@Autowired
	private DataManageService manageService;

	@RequestMapping("/add")
	public String add() {
		return data1Service.add();
	}

	@RequestMapping("/add2")
	public String add2() {
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
