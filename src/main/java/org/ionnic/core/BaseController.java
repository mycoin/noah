package org.ionnic.core;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

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
}
