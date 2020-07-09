package com.dataManage.data1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Data1Mapper {
	
	int insert(Map<String, Object> map);
	List<Map<String,Object>> getHistoryList(Map<String, Object> map);
}
