package com.breakidea.noah.framework.support.post;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public abstract class AbstractParameter implements Parameter {

	/**
	 * 获取前端提交的参数是否为 “true” 类型'true', 'on' or 'yes' (case insensitive)
	 * 
	 * @param parameterName
	 * @return
	 */
	public boolean getBoolean(String parameterName) {
		return getBoolean(parameterName, false);
	}

	/**
	 * 获取前端提交的参数是否为 “true” , 如果如存在字段返回 defaultValue
	 * 
	 * @param parameterName
	 * @return
	 */
	public boolean getBoolean(String parameterName, boolean defaultValue) {
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
		return getDouble(parameterName, 0);
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
		return getInteger(parameterName, 0);
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
		return getLong(parameterName, 0);
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
	public final String getString(String parameterName) {
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
	public final String getString(String parameterName, String defaultValue) {
		String parameter = getString(parameterName);
		if (StringUtils.isNotBlank(parameter)) {
			return parameter;
		}
		return defaultValue;
	}

}
