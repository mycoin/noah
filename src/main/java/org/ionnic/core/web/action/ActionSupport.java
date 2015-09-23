package org.ionnic.core.web.action;

import java.util.HashMap;
import java.util.Map;

public abstract class ActionSupport {

	/**
	 * @param status
	 * @param statusInfo
	 * @return
	 */
	public static Object failed(int status, Object statusInfo) {

		return null;
	}

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
}
