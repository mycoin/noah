package com.breakidea.noah.web.support.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;

public class RequestUtils {

	private static final String DEFAULT_VALUE = "";

	private static final Set<String> POSITIVE_SET = new HashSet<String>();

	private static final String SPLIT_REGEX = ",";

	private static final String FLAG_NAME = "__flag";

	static {
		POSITIVE_SET.add("true");
		POSITIVE_SET.add("T");
		POSITIVE_SET.add("Y");
	}

	/**
	 * 简单数据绑定
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T bindRequest(HttpServletRequest request, Class<T> clazz) {
		Assert.notNull(request, "HttpServletRequest must be provided");
		Assert.notNull(clazz, "Class must be provided");

		T parameter = null;
		try {
			parameter = clazz.newInstance();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(parameter);
			binder.bind(request);
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}

		return parameter;
	}

	/**
	 * 获取前端提交的参数是否为 “true” 类型
	 * 
	 * @param name
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String name) {
		String parameter = getParameter(request, name);
		if (POSITIVE_SET.contains(parameter)) {
			return true;
		}
		return false;
	}

	/**
	 * 解析获取请求标记的Flag数据
	 * 
	 * @param request
	 * @return
	 */
	public static Set<String> getFlagSet(HttpServletRequest request) {
		return getSet(request, FLAG_NAME);
	}

	/**
	 * 获取数字类型的参数值
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static Integer getInteger(HttpServletRequest request, String parameterName) {
		return getInteger(request, parameterName, 0);
	}

	/**
	 * 获取数字类型的参数值, 如果没有传递返回默认值
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static Integer getInteger(HttpServletRequest request, String parameterName, Integer defaultValue) {
		String parameter = getParameter(request, parameterName);

		if (StringUtils.hasText(parameter)) {
			return Integer.parseInt(parameter, 10);
		}

		return defaultValue;
	}

	/**
	 * 获取参数但是默认返回空字符串 “”
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameterName) {
		return getParameter(request, parameterName, DEFAULT_VALUE);
	}

	/**
	 * 获取当前请求的参数，如果没有提供当前参数返回默认参数
	 * 
	 * @param name
	 * @param defaultValue
	 * @param request
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameterName, String defaultValue) {
		Assert.notNull(request, "HttpServletRequest must be provided");
		Assert.notNull(parameterName, "parameterName must be provided");

		String requestValue = request.getParameter(parameterName);
		String parameter = StringUtils.trimWhitespace(requestValue);

		if (StringUtils.hasText(parameter)) {
			return parameter;
		}
		return defaultValue;
	}

	/**
	 * 解析请求用于标记 “标示” 功能的标签，前端默认按照逗号隔开供分割
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Set<String> getSet(HttpServletRequest request, String parameterName) {
		String parameter = getParameter(request, parameterName);
		List<String> flagList = Arrays.asList(parameter.split(SPLIT_REGEX));

		return new HashSet<String>(flagList);
	}

	/**
	 * 判断当前请求是不是异步请求的方法
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest must be provided");

		String xRequestedWith = request.getHeader("X-Requested-With");
		if (StringUtils.hasLength(xRequestedWith)) {
			return true;
		}
		return false;
	}

	/**
	 * read a cookie with special cookieName
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie readCookie(HttpServletRequest request, String cookieName) {
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
	public static String readCookieValue(HttpServletRequest servletRequest, String cookieName) {
		Cookie cookies = readCookie(servletRequest, cookieName);
		if (cookies == null) {
			return null;
		}
		return cookies.getValue();
	}

}