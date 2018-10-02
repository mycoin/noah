package com.breakidea.noah.framework.support.post;

public interface Parameter {

	/**
	 * 获取前端提交的参数是否为 “true” 类型'true', 'on' or 'yes' (case insensitive)
	 * 
	 * @param parameterName
	 * @return
	 */
	boolean getBoolean(String parameterName);

	/**
	 * 获取前端提交的参数是否为 “true” , 如果如存在字段返回 defaultValue
	 * 
	 * @param parameterName
	 * @return
	 */
	boolean getBoolean(String parameterName, boolean defaultValue);

	/**
	 * 获取 “Double” 类型的参数，默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	double getDouble(String parameterName);

	/**
	 * 获取 “Double” 类型的参数，默认返回 defaultValue
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	double getDouble(String parameterName, double defaultValue);

	/**
	 * 获取数字类型的参数值, 如果没有或者超时精度范围默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	int getInteger(String parameterName);

	/**
	 * 获取数字类型的参数值, 如果没有传递返回默认值
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	int getInteger(String parameterName, int defaultValue);

	/**
	 * 获取 “Long” 类型的参数，默认返回 0
	 * 
	 * @param parameterName
	 * @return
	 */
	long getLong(String parameterName);

	/**
	 * 获取 “Long” 类型的参数，默认返回 defaultValue
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	long getLong(String parameterName, long defaultValue);

	/**
	 * 获取参数但是默认返回空字符串 “”
	 * 
	 * @param parameterName
	 * @return
	 */
	String getString(String parameterName);

	/**
	 * 获取当前请求的参数，如果没有提供当前参数返回 defaultValue 参数
	 * 
	 * @param parameterName
	 * @param defaultValue
	 * @return
	 */
	String getString(String parameterName, String defaultValue);
}
