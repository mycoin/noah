package com.breakidea.lotus.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.breakidea.lotus.common.base.Service;

public abstract class ServicerReigsterUtils {

	private static final Map<String, Map<String, Service>> register = new HashMap<String, Map<String, Service>>();

	public static void reigsterService(Service service) {
		Map<String, Service> map = register.get(service.getType());
		if (map == null) {
			synchronized (register) {
				map = register.get(service.getType());
				if (map == null) {
					map = new HashMap<String, Service>();
					register.put(service.getType(), map);
				}
			}
		}

		map.put(service.getName(), service);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Service> T findService(String type, String name) {
		Map<String, Service> map = register.get(type);
		if (map == null) {
			return null;
		}
		return (T) map.get(name);
	}
}
