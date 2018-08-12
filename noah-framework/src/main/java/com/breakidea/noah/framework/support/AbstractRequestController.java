package com.breakidea.noah.framework.support;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.WebRequest;

public class AbstractRequestController {

	public static final String REQUEST_ATTRIBUTE = "request";

	public static final String SESSION_ATTRIBUTE = "session";

	public static final String RESPONSE_ATTRIBUTE = "response";

	protected Log logger = LogFactory.getLog(getClass());

	@Resource
	protected ServletContext servletContext;
	
	@Resource
	protected ApplicationContext applicationContext;

	@Resource
	protected WebRequest webRequest;

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpServletResponse response;
}
