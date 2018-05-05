package com.breakidea.noah.shared.service;

import java.util.Map;

import com.breakidea.noah.shared.exception.ServiceException;

public interface MonitorService {

	void save(Map<String, Object> resultMap) throws ServiceException;

}
