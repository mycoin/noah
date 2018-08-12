package com.breakidea.noah.service.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.breakidea.noah.framework.support.post.Parameter;

public class PostParameter implements Parameter {

	private Map<String, Object> requestParams = new HashMap<String, Object>();

	private HttpServletRequest request;

	private String requestUrl = null;

	public PostParameter(HttpServletRequest request) {
		this.request = request;
	}

	public Object get(String key) {
		return requestParams.get(key);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public Map<String, Object> getRequestParams() {
		return requestParams;
	}

	public String getRequestUrl() {
		return requestUrl;
	}
}
