package com.breakidea.noah.dao;

import java.util.Map;

import com.breakidea.noah.dao.annotation.AutowireMapper;

@AutowireMapper
public interface MonitorDao {

	public void insert(Map<String, Object> param);

}