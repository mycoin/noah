package org.ionnic.core.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String addr = request.getHeader("x-forwarded-for");
		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getHeader("Proxy-Client-IP");
		}
		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getRemoteAddr();
		}
		if (addr.equals("0:0:0:0:0:0:0:1")) {
			addr = LOCAL;
		}
		return addr;
	}

	public static final String LOCAL = "LOCAL";

}
