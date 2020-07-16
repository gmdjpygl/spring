package com.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.Data1Mapper;
import com.data.dao.Data2Mapper;

@Service
public class Data1Service {
	@Autowired
	private Data1Mapper dataMapper;
	public String  add() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", UUID.randomUUID());
		params.put("confId","11");
		int update = dataMapper.insert(params);
		return update>0?"成功":"失败";
	}
	
}
