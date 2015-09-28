package org.ionnic.core.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 * 
 */
public abstract class ActionSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @param status
	 * @param dataMap
	 * @return
	 */
	public static Object result(int status, Map<String, Object> dataMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		if (status == 0) {
			result.put("data", dataMap);
		} else {
			result.put("statusInfo", dataMap);
		}
		return result;
	}

	/**
	 * @param data
	 * @return
	 */
	public static Object success(Object data) {

		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @param action
	 */
	public void init(HttpServletRequest request, HttpServletResponse response, HandlerMethod action) {

	};
}
