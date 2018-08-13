package com.breakidea.noah.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class WebContext extends ModelAndView {

	public static final String REQUEST_ATTRIBUTE = "request";

	public static final String SESSION_ATTRIBUTE = "session";

	public static final String RESPONSE_ATTRIBUTE = "response";

	private HttpServletRequest request;

	private HttpServletResponse response;

	public WebContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		addObject(REQUEST_ATTRIBUTE, request);
		addObject(SESSION_ATTRIBUTE, request.getSession());
		addObject(RESPONSE_ATTRIBUTE, response);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
