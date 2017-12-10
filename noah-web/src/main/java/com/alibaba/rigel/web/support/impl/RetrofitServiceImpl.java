package com.alibaba.rigel.web.support.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.rigel.framework.FrameworkConstants;
import com.alibaba.rigel.web.support.RetrofitService;

@Service
public class RetrofitServiceImpl implements RetrofitService, FrameworkConstants {

	@Resource
	private HttpServletRequest request;

	@Override
	public String getRequestId() {
		if (request.getAttribute(REQUEST_ID) == null) {
			request.setAttribute(REQUEST_ID, UUID.randomUUID());
		}
		
		return request.getAttribute(REQUEST_ID) + "";
	}
}
