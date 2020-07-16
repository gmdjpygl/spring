package com.data.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.data.dao.Data1Mapper;
import com.data.dao.Data2Mapper;

@Service
public class Data2Service {
	@Autowired
	private Data2Mapper dataMapper;
	public String  add() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", UUID.randomUUID());
		params.put("confId","11");
		params.put("dataTime",new Date());
		params.put("dataValue",11);
		int update = dataMapper.insertData(params);
		return update>0?"成功":"失败";
	}
}
