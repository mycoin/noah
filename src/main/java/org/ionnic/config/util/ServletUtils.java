package org.ionnic.config.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;

public class ServletUtils extends ServletRequestUtils {

	/**
	 * @param req
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(HttpServletRequest req, Class<T> requiredType) {

		try {
			return (T) req.getAttribute(requiredType.getName());
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param req
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest req, String name) {
		for (Cookie item : req.getCookies()) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * @param req
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
				// According to the network card to get the machine
				// configuration IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// For the case of multiple agents, the first IP for the client to real
		// IP, multiple IP according to ',' segmentation
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	/**
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	/**
	 * @param req
	 * @param requiredType
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest req, Class<?> requiredType, Object value) {
		Assert.isInstanceOf(requiredType, value, "value not instance of " + requiredType);
		String key = requiredType.getName();

		req.setAttribute(key, value);
	}


}
