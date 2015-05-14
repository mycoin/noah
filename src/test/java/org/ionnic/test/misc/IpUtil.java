package org.ionnic.test.misc;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	public static final String LOCAL = "LOCAL";

	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIPAddr(HttpServletRequest request) {
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

}
