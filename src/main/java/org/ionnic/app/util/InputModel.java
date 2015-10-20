package org.ionnic.app.util;

import java.util.Map;

import org.ionnic.config.util.JsonUtils;

/**
 * @author apple
 * 
 */
public class InputModel {

	private String token;

	private Map<String, Object> params;

	private String method;

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
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

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return
	 */
	public String toJSON() {
		return JsonUtils.toJson(this);
	}
}
