package org.ionnic.app.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ionnic.common.util.JsonUtils;
import org.springframework.http.HttpInputMessage;

public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> params;

	private String method = "GET";

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return
	 */
	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		return params;
	}

	/**
	 * @param inputMessage
	 */
	public void init(HttpInputMessage inputMessage) {

	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}