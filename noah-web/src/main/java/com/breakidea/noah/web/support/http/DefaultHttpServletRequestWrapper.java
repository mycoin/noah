package com.breakidea.noah.web.support.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DefaultHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public DefaultHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

}
