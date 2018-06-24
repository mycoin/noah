package com.breakidea.noah.web.support.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.breakidea.noah.framework.FrameworkConstants;
import com.breakidea.noah.web.support.RetrofitService;

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
