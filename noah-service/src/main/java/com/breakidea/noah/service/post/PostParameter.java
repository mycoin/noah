package com.breakidea.noah.service.post;

import javax.servlet.http.HttpServletRequest;

import com.breakidea.noah.framework.support.post.AbstractParameter;

public class PostParameter extends AbstractParameter {

	private HttpServletRequest request;

	public PostParameter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String[] getParameter(String parameterName) {
		return request.getParameterValues(parameterName);
	}
}
