package com.breakidea.lotus.web.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import com.breakidea.lotus.shared.Constants;

public abstract class Utils implements Constants {

	private static final char SEPARATOR = '_';

	/**
	 * 组装一个URL地址，带有参数的那种！
	 *
	 * @param urlName
	 * @param param
	 * @return
	 */
	public static String buildLink(String urlName, Map<String, Object> paramData) {
		StringWriter param = new StringWriter();
		for (String name : paramData.keySet()) {
			Object value = paramData.get(name);
			if (value == null) {
				continue;
			}
			param.append("&" + name + "=" + encodeUrl(value));
		}

		String queryString = param.toString();
		if (queryString.length() > 2) {
			return urlName + (urlName.indexOf("?") > -1 ? "&" : "?") + param.toString().substring(1);
		}

		return urlName;
	}

	/**
	 * HTML编码
	 *
	 * @param {String}
	 *            source
	 * @return
	 */
	public static String encodeHtml(Object subject) {
		if (null == subject) {
			return "";
		}

		return HtmlUtils.htmlEscape(subject.toString());
	}

	/**
	 * @param subject
	 * @return
	 */
	public static String encodeUnicode(Object subject) {
		if (null == subject) {
			return "";
		} else {
			String source = subject.toString();
			String result = "";

			for (int i = 0; i < source.length(); i++) {
				int chr = source.charAt(i);
				if (chr >= 19968 && chr <= 171941) {
					result += "\\u" + Integer.toHexString(chr);
				} else {
					result += source.charAt(i);
				}
			}
			return result;
		}
	}

	/**
	 * 编码 URL 字符串
	 *
	 * @param {String}
	 *            string
	 * @return string
	 */
	public static String encodeUrl(Object subject) {
		String string = trim(subject);
		String decoded = null;
		try {
			decoded = URLEncoder.encode(string, Constants.CHARSET);
			return decoded.replaceAll("\\+", "%20");
		} catch (Exception e) {

		}
		return string;
	}

	/**
	 * 在普通JS环境需要将影响JS语法环境的字符串转义
	 *
	 * @public
	 * @param {String}
	 *            source 原始字符串
	 * @return string
	 */
	public static String escapeJs(Object subject) {
		if (null == subject) {
			return "";
		} else {
			String source = subject.toString();
			return JavaScriptUtils.javaScriptEscape(source);
		}
	}

	/**
	 * @return
	 */
	public static String getGuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @param subject
	 * @return
	 */
	public static String raw(Object subject) {
		if (subject == null) {
			return "";
		} else {
			return subject.toString();
		}
	}

	/**
	 * Removes HTML tags from the string value of the specified object and
	 * returns the resulting string.
	 *
	 * @param obj
	 */
	public static String stripTags(Object obj) {
		List<String> allowTags = new ArrayList<String>();

		allowTags.add("a");
		allowTags.add("span");
		allowTags.add("p");
		allowTags.add("div");

		return stripTags(obj, allowTags);
	}

	/**
	 * Removes all not allowed HTML tags from the string value of the specified
	 * object and returns the resulting string.
	 *
	 * @param obj
	 * @param allowedTags
	 *            An array of allowed tag names (i.e. "h1","br","img")
	 */
	public static String stripTags(Object obj, List<String> allowedTags) {
		if (obj == null) {
			return null;
		}

		String subject = obj.toString();

		// build list of tags to be used in regex pattern
		StringBuilder allowedTagList = new StringBuilder();
		String tagRule = "<[^>]*?>";

		if (allowedTags != null) {

			for (String tag : allowedTags) {
				if (tag != null && tag.matches("[a-zA-Z0-9]+")) {
					if (allowedTagList.length() > 0) {
						allowedTagList.append("|");
					}
					allowedTagList.append(tag);
				}
			}
		}
		if (allowedTagList.length() > 0) {
			tagRule = "<(?!/?(" + allowedTagList.toString() + ")[\\s>/])[^>]*?>";
		}
		return Pattern.compile(tagRule, Pattern.CASE_INSENSITIVE).matcher(subject).replaceAll("");
	}

	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * trim 字符串函数
	 *
	 * @public
	 * @param {String}
	 *            source
	 * @return
	 */
	public static String trim(Object source) {
		if (null == source) {
			return "";
		}
		return (source + "").trim();
	}

	/**
	 * Limits the string value of 'truncateMe' to 'maxLength' characters. If the
	 * string gets curtailed, the configured suffix (default is "...") is used
	 * as the ending of the truncated string.
	 *
	 * @param maxLength
	 *            An int with the maximum length.
	 * @param truncateMe
	 *            The value to be truncated.
	 * @return A String.
	 */
	public static String truncate(Object truncateMe, int maxLength) {
		return truncate(truncateMe, maxLength, "...");
	}

	/**
	 * Limits the string value of 'truncateMe' to the specified max length in
	 * characters. If the string gets curtailed, the specified suffix is used as
	 * the ending of the truncated string.
	 *
	 * @param truncateMe
	 *            The value to be truncated.
	 * @param maxLength
	 *            An int with the maximum length.
	 * @param suffix
	 *            A String.
	 * @return A String.
	 */
	public static String truncate(Object truncateMe, int maxLength, String suffix) {
		return truncate(truncateMe, maxLength, suffix, true);
	}

	/**
	 * Limits the string value of 'truncateMe' to the latest complete word
	 * within the specified maxLength. If the string gets curtailed, the
	 * specified suffix is used as the ending of the truncated string.
	 *
	 * @param truncateMe
	 *            The value to be truncated.
	 * @param maxLength
	 *            An int with the maximum length.
	 * @param suffix
	 *            A String.
	 * @param defaultTruncateAtWord
	 *            Truncate at a word boundary if true.
	 * @return A String.
	 */
	public static String truncate(Object truncateMe, int maxLength, String suffix, boolean defaultTruncateAtWord) {
		if (truncateMe == null || maxLength <= 0) {
			return null;
		}

		String string = String.valueOf(truncateMe);
		if (string.length() <= maxLength) {
			return string;
		}
		if (suffix == null || maxLength - suffix.length() <= 0) {
			// either no need or no room for suffix
			return string.substring(0, maxLength);
		}
		if (defaultTruncateAtWord) {
			// find the latest space within maxLength
			int lastSpace = string.substring(0, maxLength - suffix.length() + 1).lastIndexOf(" ");
			if (lastSpace > suffix.length()) {
				return string.substring(0, lastSpace) + suffix;
			}
		}
		// truncate to exact character and append suffix
		return string.substring(0, maxLength - suffix.length()) + suffix;
	}

	/**
	 * @param exception
	 * @return
	 */
	public static Writer printException(Throwable exception) {
		Writer stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter);
		exception.printStackTrace(pw);
		return stringWriter;
	}
}
