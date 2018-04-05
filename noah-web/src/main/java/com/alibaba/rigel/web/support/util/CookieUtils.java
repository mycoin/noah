package com.alibaba.rigel.web.support.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtils
 *
 * @author David Czarnecki
 */
public abstract class CookieUtils {

	public static Cookie getCookie(HttpServletRequest httpServletRequest, String cookieKey) {
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(cookieKey)) {
				return cookie;
			}
		}

		return null;
	}

	public static void addCookie(HttpServletResponse httpServletResponse, String cookieKey, String cookieValue) {
		httpServletResponse.addCookie(createCookie(cookieKey, cookieValue));
	}

	public static Cookie createCookie(String cookieKey, String cookieValue) {
		Cookie cookie = new Cookie(cookieKey, cookieValue);

		cookie.setHttpOnly(true);
		cookie.setSecure(true);

		return cookie;
	}
}