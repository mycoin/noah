package com.breakidea.noah.framework.support.from;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public abstract class Parameter {

	public static final boolean DEFAULT_BOOLEAN = false;

	public static final int DEFAULT_INT = 0;

	public static final double DEFAULT_DOUBLE = 0L;

	/**
	 * 获取前端提交的参数是否为 “true” 类型'true', 'on' or 'yes' (case insensitive)
	 * 
	 * @param parameterName
	 * @return
	 */
	public Boolean getBoolean(String parameterName) {
		return getBoolean(parameterName, DEFAULT_BOOLEAN);
	}

	/**
	 * 获取前端提交的参数是否为 “true” , 如果如存在字段返回 defaultValue
	 * 
	 * @param parameterName
	 * @return
	 */
	public Boolean getBoolean(String parameterName, boolean defaultValue) {
		String parameter = getString(parameterName, null);
		if (parameter == null) {
			return defaultValue;
		}
		return BooleanUtils.toBoolean(parameter);
	}

	/**
	 * 获取 “Double” 类型的参数，默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	public double getDouble(String parameterName) {
		return getDouble(parameterName, DEFAULT_DOUBLE);
	}

	/**
	 * 获取 “Double” 类型的参数，默认返回 defaultValue
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	public double getDouble(String parameterName, double defaultValue) {
		return NumberUtils.toDouble(getString(parameterName, null), defaultValue);
	}

	/**
	 * 获取数字类型的参数值, 如果没有或者超时精度范围默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	public int getInteger(String parameterName) {
		return getInteger(parameterName, DEFAULT_INT);
	}

	/**
	 * 获取数字类型的参数值, 如果没有传递返回默认值
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	public int getInteger(String parameterName, int defaultValue) {
		return NumberUtils.toInt(parameterName, defaultValue);
	}

	/**
	 * 获取 “Long” 类型的参数，默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	public long getLong(String parameterName) {
		return getLong(parameterName, DEFAULT_INT);
	}

	/**
	 * 获取 “Long” 类型的参数，默认返回 defaultValue
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	public long getLong(String parameterName, long defaultValue) {
		return NumberUtils.toLong(getString(parameterName, null), defaultValue);
	}

	public abstract String[] getParameter(String parameterName);

	/**
	 * 获取参数但是默认返回空字符串 “”
	 * 
	 * @param parameterName
	 * @return
	 */
	public String getString(String parameterName) {
		String[] parameter = getParameter(parameterName);
		if (parameter == null) {
			return null;
		}

		return StringUtils.trimToNull(parameter[0]);
	}

	/**
	 * 获取当前请求的参数，如果没有提供当前参数返回 defaultValue 参数
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	public String getString(String parameterName, String defaultValue) {
		String parameter = getString(parameterName);
		if (StringUtils.isNotBlank(parameter)) {
			return parameter;
		}
		return null;
	}

	/**
	 * 解析请求用于标记 “标示” 功能的标签，前端默认按照逗号隔开供分割
	 * 
	 * @param parameterName
	 * @return
	 */
	public List<String> getStringList(String parameterName) {
		return getStringList(parameterName, null);
	}

	/**
	 * 获取相同参数的列表
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	public List<String> getStringList(String parameterName, List<String> defaultValue) {
		String[] parameter = getParameter(parameterName);
		if (parameter == null || parameter.length == 0) {
			return defaultValue;
		}
		
		return Arrays.asList(parameter);
	}

}
