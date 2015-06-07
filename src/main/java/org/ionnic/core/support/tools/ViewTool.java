package org.ionnic.core.support.tools;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * 模板用到的基本函数
 * 
 * @author apple
 */
public class ViewTool {

	/**
	 * 编码 URL 字符串
	 * 
	 * @param {String} string
	 * @return string
	 */
	public static String encodeUrl(String string) {
		try {
			return URLEncoder.encode(trim(string), "utf-8");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * HTML编码
	 * 
	 * @param {String} source
	 * @return
	 */
	public static String escapeInH(String source) {
		if (null == source) {
			source = "";
		}
		source = source.replaceAll("\\&", "&amp;");
		source = source.replaceAll("\\\"", "&quot;");
		source = source.replaceAll("\\<", "&lt;");
		source = source.replaceAll("\\>", "&gt;");

		return source;
	}

	/**
	 * 在普通JS环境需要将影响JS语法环境的字符串转义
	 * 
	 * @public
	 * @param {String} source 原始字符串
	 * @return string
	 */
	public static String escapeInJ(Object subject) {
		if (null == subject) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			String source = subject.toString();
			int length = source.length();

			for (int i = 0; i < length; i++) {
				char c = source.charAt(i);
				switch (c) {
				case 39:
					sb.append("\\'");
					break;
				case 34:
					sb.append("\\\"");
					break;
				case 47:
					sb.append("\\/");
					break;
				case 92:
					sb.append("\\\\");
					break;
				case 13:
					sb.append("\\r");
					break;
				case 10:
					sb.append("\\n");
					break;
				default:
					sb.append(c);
					break;
				}
			}
			return sb + "";
		}
	}

	/**
	 * 格式化string为Date
	 * 
	 * @param datestr
	 * @return date
	 * @throws Exception
	 */
	public static Date getDate(String string) {
		String format = null;
		if ("now".equalsIgnoreCase(string)) {
			return new Date();
		}
		if (string.indexOf(':') > 0) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else {
			format = "yyyy-MM-dd";
		}
		string = string.replaceAll("/", "-");
		try {
			return new SimpleDateFormat(format, Locale.CHINA).parse(string);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * get GUID
	 * 
	 * @return
	 */
	public static String getGuid() {
		return "" + UUID.randomUUID();
	}

	/**
	 * 判断是否空字符串，无效字符串
	 * 
	 * @public
	 * @param string
	 * @return
	 */
	public static boolean isBlank(String source) {
		source = trim(source);
		return "".equals(source);
	}

	/**
	 * 从字符串中去除 HTML标记
	 * 
	 * @public
	 * @param {String} html
	 * @return text
	 */
	public static String stripTags(String source) {
		if (null == source) {
			return "";
		}
		return source.replaceAll("\\<.*?>", "");
	}

	/**
	 * @param format
	 * @return
	 */
	public static String toLocateDate(String format) {
		return toLocateDate(format, new Date());
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String toLocateDate(String format, Date date) {
		if ("default".equalsIgnoreCase(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if ("short".equalsIgnoreCase(format)) {
			format = "yyyy-MM-dd";
		} else if ("time".equalsIgnoreCase(format)) {
			return "" + date.getTime();
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * trim 字符串函数
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static String trim(Object source) {
		if (null == source) {
			source = "";
		}
		return (source + "").trim();
	}

	/**
	 * 截取字符串
	 * 
	 * @public
	 * @param {String} target 原始字符串
	 * @param {int} maxLength 要截取最大长度
	 * @param {String} tail 追加字符串
	 * @return string
	 */
	public static String truncate(String target, int maxLength, String tail) {
		if (target == null || target.length() <= maxLength) {
			return target;
		}
		int length = target.length();
		char c = ' ';
		String tag = null;
		String name = null;
		String result = "";
		boolean isTag = false;
		List<String> tags = new ArrayList<String>();
		int i = 0;
		for (int end = 0, spanEnd = 0; i < length && maxLength > 0; i++) {
			c = target.charAt(i);
			if (c == '<') {
				end = target.indexOf('>', i);
			}
			if (end > 0) {
				tag = target.substring(i, end + 1);
				int n = tag.length();
				if (tag.endsWith("/>")) {
					isTag = true;
				} else if (tag.startsWith("</")) {
					name = tag.substring(2, end - i);
					int size = tags.size() - 1;

					if (size >= 0 && name.equals(tags.get(size))) {
						isTag = true;
						tags.remove(size);
					}
				} else {
					spanEnd = tag.indexOf(' ', 0);
					spanEnd = spanEnd > 0 ? spanEnd : n;
					name = tag.substring(1, spanEnd);
					if (name.trim().length() > 0) {

						spanEnd = target.indexOf("</" + name + ">", end);
						if (spanEnd > 0) {
							isTag = true;
							tags.add(name);
						}
					}
				}
				if (!isTag) {
					if (n >= maxLength) {
						result += tag.substring(0, maxLength);
						break;
					} else {
						maxLength -= n;
					}
				}
				result += tag;
				isTag = false;
				i = end;
				end = 0;
			} else {
				maxLength--;
				result += c;
			}
		}
		for (String endTag : tags) {
			result += "</" + endTag + ">";
		}
		if (i < length) {
			result += (tail == null ? "" : tail);
		}
		return result;
	}
}
