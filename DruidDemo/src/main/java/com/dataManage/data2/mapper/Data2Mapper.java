package com.dataManage.data2.mapper;

import java.util.List;
import java.util.Map;

public interface Data2Mapper {
	int insertData(Map<String, Object> map);

	int updateData(Map<String, Object> map);

	int insertHistory(Map<String, Object> map);

	int deleteDataHistory(Map<String, Object> map);

	List<Map<String, Object>> getDataList(Map<String, Object> map);
}
