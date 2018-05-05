package com.breakidea.noah.web.support.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

/**
 * Cookie utils
 *
 * @author David Czarnecki
 */
public abstract class CookieUtils {

	/**
	 * add a cookie with special cookieName and cookieValue
	 * 
	 * @param servletResponse
	 * @param cookieKey
	 * @param cookieValue
	 */
	public static void addCookie(HttpServletResponse response, String cookieKey, String cookieValue) {
		addCookie(response, cookieKey, cookieValue, false);
	}

	/**
	 * add a cookie with special cookieName and cookieValue
	 * 
	 * @param response
	 * @param cookieKey
	 * @param cookieValue
	 */
	public static void addCookie(HttpServletResponse response, String cookieKey, String cookieValue, Boolean safeMode) {
		Assert.notNull(response, "HttpServletResponse must be provided");
		Assert.notNull(cookieKey, "cookieKey must be provided");

		Cookie targetCookie = new Cookie(cookieKey, cookieValue);
		if (safeMode) {
			try {
				targetCookie.setSecure(true);
				targetCookie.setHttpOnly(true);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		response.addCookie(targetCookie);
	}

	/**
	 * read a cookie with special cookieName
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Assert.notNull(request, "HttpServletRequest must be provided");
		Assert.notNull(cookieName, "cookieName must be provided");

		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookieName == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equalsIgnoreCase(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * 获取Cookie字符串的方法
	 * 
	 * @param servletRequest
	 * @param cookieName
	 * @return
	 */
	public static String getCookieString(HttpServletRequest servletRequest, String cookieName) {
		Cookie cookies = getCookie(servletRequest, cookieName);
		if (cookies == null) {
			return null;
		}
		return cookies.getValue();
	}
}