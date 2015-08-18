package org.ionnic.core.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action {

	/**
	 * @param req
	 * @param resp
	 */
	public void init(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req);
	}
}
