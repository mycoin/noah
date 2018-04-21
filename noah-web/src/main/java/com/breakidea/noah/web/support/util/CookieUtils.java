package com.breakidea.noah.web.support.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie utils
 *
 * @author David Czarnecki
 */
public abstract class CookieUtils {

	/**
	 * read a cookie with special cookieName
	 * 
	 * @param servletRequest
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest servletRequest, String cookieName) {
		Cookie[] cookies = servletRequest.getCookies();
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
	 * add a cookie with special cookieName and cookieValue
	 * 
	 * @param servletResponse
	 * @param cookieKey
	 * @param cookieValue
	 */
	public static void addCookie(HttpServletResponse servletResponse, String cookieKey, String cookieValue) {
		servletResponse.addCookie(new Cookie(cookieKey, cookieValue));
	}
}