package org.ionnic.core.utils;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

	/**
	 * @param req
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest req, String name) {
		if (req.getCookies() == null) {
			return null;
		} else {
			for (Cookie item : req.getCookies()) {
				if (item.getName().equals(name)) {
					return item;
				}
			}
			return null;
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static URL getRefer(HttpServletRequest request) {
		try {
			String url = request.getHeader("Referer");
			return new URL(url);
		} catch (MalformedURLException e) {
			// noop
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
	 * @param req
	 * @param name
	 * @return
	 */
	public static Object getSession(HttpServletRequest req, String name) {
		HttpSession session = req.getSession(true);
		if (null == session) {
			return null;
		} else {
			return session.getAttribute(name);
		}
	}

	/**
	 * @param req
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest req) {
		String requestedWith = req.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

}
