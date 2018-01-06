package com.lz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lz.dao.LogMapper;
import com.lz.entity.Log;
@Service
public class LogService {
	@Autowired
	private LogMapper logMapper;
	public int insertSelective(Log record){
		return logMapper.insertSelective(record);
	}
	
}
