package com.dataManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dataManage.data1.mapper.Data1Mapper;
import com.dataManage.data2.mapper.Data2Mapper;
import com.dataManage.util.DateUtil;
import com.dataManage.util.GsonUtil;
import com.dataManage.util.HttpClientUtil;

@Service
public class DataManageService {
	@Autowired
	private Data1Mapper data1Mapper;
	@Autowired
	private Data2Mapper data2Mapper;
	@Autowired
	private Environment env;
	
	public String add() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", UUID.randomUUID());
		params.put("confId", "11");
		params.put("dataTime", new Date());
		params.put("dataValue", 11);
		int update = data2Mapper.insertData(params);
		return update > 0 ? "成功" : "失败";
	}
	public List<Map<String,Object>> getDataHistory(Map<String, String> params ) {
		String confId = params.get("confId");
		Date startDate = DateUtil.parse(params.get("startDate"),"yyyy-MM-dd HH:mm:ss");
		Date endDate = DateUtil.parse(params.get("endDate"),"yyyy-MM-dd HH:mm:ss");
		Map<String,Object> qMap = new HashMap<String, Object>();
		qMap.put("conf_id", confId);
		qMap.put("start_date", startDate);
		qMap.put("end_date", endDate);
		return data1Mapper.getHistoryList(qMap);
	}

	public String  extractData() {
		// 1.查询上次抽取时间
		List<Map<String, Object>> lastDataList = data2Mapper.getDataList(null);
		lastDataList = transitionKey(lastDataList);
		// 2.查询抽取数据
		for (Map<String, Object> lastDataMap : lastDataList) {
			if (lastDataMap.containsKey("data_time")) {
				lastDataMap.put("start_data_time", lastDataMap.get("data_time"));
			}
			
			List<Map<String, Object>> hList = data1Mapper.getHistoryList(lastDataMap);
			hList = transitionKey(hList);

			// 3.插入数据表
				// 防止数据重复,先删除同时间的
				data2Mapper.deleteDataHistory(lastDataMap);
			for (Map<String, Object> dataMap : hList) {
				// 插入历史表
				data2Mapper.insertHistory(dataMap);
				// 插入更新实时表
				List<Map<String,Object>> dList = data2Mapper.getDataList(dataMap);
				dList = transitionKey(dList);
				if(dList!=null && dList.size()>0 && dList.get(0).containsKey("data_time")) {
					data2Mapper.updateData(dataMap);
				}else {
					data2Mapper.insertData(dataMap);
				}
			}
		}
		return "";
	}
	public String  extractHttpData() {
		// 1.查询上次抽取时间
		List<Map<String, Object>> lastDataList = data2Mapper.getDataList(null);
		lastDataList = transitionKey(lastDataList);
		String url = env.getProperty("extract.url");
		for (Map<String, Object> lastDataMap : lastDataList) {
			
			// 2.查询抽取数据
			Map<String,Object> httpParams = new HashMap<String, Object>();
			httpParams.put("confId", lastDataMap.get("conf_id"));
			if (lastDataMap.containsKey("data_time")) {
				httpParams.put("startDate", lastDataMap.get("data_time"));
			}else {
				httpParams.put("startDate", DateUtil.getDayFirst(new Date()));
			}
			String httpJson = GsonUtil.BeanToJson(httpParams);
			
			String dataJson = HttpClientUtil.doHttpsPost(url,httpJson);
			if("".equals(dataJson)) {
				continue;
			}
			Map<String,Object> reMap = GsonUtil.GsonToMaps(dataJson);
			if(!"200".equals(reMap.get("code"))){
				continue;
			}
			List<Map<String, Object>> hList = (List<Map<String, Object>>)reMap.get("data");
			
			hList = transitionKey(hList);
			// 3.插入数据表
			// 防止数据重复,先删除同时间的
			data2Mapper.deleteDataHistory(lastDataMap);
			for (Map<String, Object> dataMap : hList) {
				dataMap.put("data_time", DateUtil.parse((String)dataMap.get("data_time"), "yyyy-MM-dd HH:mm:ss"));
				
				// 插入历史表
				data2Mapper.insertHistory(dataMap);
				// 插入更新实时表
				List<Map<String,Object>> dList = data2Mapper.getDataList(dataMap);
				dList = transitionKey(dList);
				if(dList!=null && dList.size()>0 && dList.get(0).containsKey("data_time")) {
					data2Mapper.updateData(dataMap);
				}else {
					data2Mapper.insertData(dataMap);
				}
			}
		}
		return "1";
	}

	public static List<Map<String, Object>> transitionKey(List<Map<String, Object>> list) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				Map<String, Object> newMap = new HashMap<String, Object>();
				for (String key : map.keySet()) {
					if (key != null) {
						newMap.put(key.toLowerCase(), map.get(key));
					}
				}
				newlist.add(newMap);
			}
		}
		return newlist;
	}

}
