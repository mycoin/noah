package org.ionnic.core.support.layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author apple
 */
public class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
	private final String method;

	/**
	 * @param request
	 * @param method
	 */
	public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
		super(request);
		this.method = method;
	}

	@Override
	public String getMethod() {
		return this.method;
	}
}
