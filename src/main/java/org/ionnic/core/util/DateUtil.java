package org.ionnic.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 简单断言函数
	 * 
	 * @param condition
	 * @param message
	 */
	private static void declare(Object condition, String message) {
		if (!(Boolean) condition) {
			throw new AssertionError(message);
		}
	}

	/**
	 * 日期转化为String
	 * 
	 * @param date
	 * @return date string
	 */
	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT);
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String format(Date date, String format) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(date);
		} catch (Throwable e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String string = "2012/08/22 12:32:56";
		Date date = parse(string);
		// parseDate
		declare("Wed Aug 22 12:32:56 CST 2012".equals(date.toString()), "write error.");
		// formatDate
		declare(string.equals(format(date, "yyyy/MM/dd HH:mm:ss")), "");
		System.out.println("OK");
	}

	/**
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 格式化string为Date
	 * 
	 * @param datestr
	 * @return date
	 */
	public static Date parse(String string) {
		if (null == string || "".equals(string)) {
			return null;
		}
		if (StringUtil.isNumeric(string)) {
			return new Date(Long.parseLong(string));
		}
		try {
			String format = null;
			if (string.indexOf(':') > 0) {
				format = "yyyy-MM-dd HH:mm:ss";
			} else {
				format = "yyyy-MM-dd";
			}
			string = string.replaceAll("/", "-");
			return new SimpleDateFormat(format, Locale.CHINA).parse(string);
		} catch (Throwable e) {
			return null;
		}
	}
}
