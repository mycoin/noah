package com.breakidea.noah.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.breakidea.noah.dao.MonitorDao;
import com.breakidea.noah.shared.exception.ServiceException;
import com.breakidea.noah.shared.service.MonitorService;

@Service
public class MonitorServiceimpl implements MonitorService {

	@Resource
	private MonitorDao monitorDao;

	@Override
	public void save(Map<String, Object> resultMap) throws ServiceException {
		monitorDao.insert(resultMap);
	}

}
