package org.ionnic.core.utils;import java.net.URLEncoder;import java.util.UUID;import java.util.regex.Matcher;import java.util.regex.Pattern;/** * 模板用到的基本函数 * * @author apple * @param <E> */public class StringUtils extends org.apache.commons.lang.StringUtils {		public static String DEFAULT_CHARSET = "UTF-8";	/**	 * @param subject	 * @return	 */	public static String encodeUnicode(Object subject) {		if (null == subject) {			return "";		} else {			String source = subject.toString();			String result = "";			for (int i = 0; i < source.length(); i++) {				int chr = source.charAt(i);				if (chr >= 19968 && chr <= 171941) {					result += "\\u" + Integer.toHexString(chr);				} else {					result += source.charAt(i);				}			}			return result;		}	}	/**	 * HTML编码	 *	 * @param {String} source	 * @return	 */	public static String escapeInH(Object subject) {		if (null == subject) {			return "";		} else {			String source = subject.toString();			source = source.replaceAll("\\&", "&amp;");			source = source.replaceAll("\\\"", "&quot;");			source = source.replaceAll("\\<", "&lt;");			source = source.replaceAll("\\>", "&gt;");			return source;		}	}	/**	 * 在普通JS环境需要将影响JS语法环境的字符串转义	 *	 * @public	 * @param {String} source 原始字符串	 * @return string	 */	public static String escapeInJ(Object subject) {		if (null == subject) {			return "";		} else {			StringBuffer buffer = new StringBuffer();			String source = subject.toString();			int length = source.length();			for (int i = 0; i < length; i++) {				char c = source.charAt(i);				switch (c) {				case 39:					buffer.append("\\'");					break;				case 34:					buffer.append("\\\"");					break;				case 47:					buffer.append("\\/");					break;				case 92:					buffer.append("\\\\");					break;				case 13:					buffer.append("\\r");					break;				case 10:					buffer.append("\\n");					break;				default:					buffer.append(c);					break;				}			}			return buffer.toString();		}	}	/**	 * 编码 URL 字符串	 *	 * @param {String} string	 * @return string	 */	public static String escapeInU(Object subject) {		String string = trim(subject);		try {			return URLEncoder.encode(string, DEFAULT_CHARSET);		} catch (Exception e) {		}		return string;	}	/**	 * get GUID	 *	 * @return	 */	public static String getGuid() {		return UUID.randomUUID().toString();	}	/**	 * 判断是否空字符串，无效字符串	 *	 * @public	 * @param string	 * @return	 */	public static boolean isBlank(Object source) {		String tmp = trim(source);		return tmp.length() > 0;	}	/**	 * 字符串替换函数	 *	 * @public	 * @param {String} source 目标字符串	 * @param {String} pattern 正则源码	 * @param {String} dest 替换值	 * @return string result	 */	public static String replaceAll(Object source, String pattern, String dest) {		if (null == source) {			return "";		}		Matcher match = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(source.toString());		return match.replaceAll(dest);	}	/**	 * trim 字符串函数	 *	 * @public	 * @param {String} source	 * @return	 */	public static String trim(Object source) {		if (null == source) {			source = "";		}		return (source + "").trim();	}}