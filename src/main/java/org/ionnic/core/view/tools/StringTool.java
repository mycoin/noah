package org.ionnic.core.view.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ionnic.core.utils.StringUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author apple
 * 
 */
public class StringTool extends StringUtils {

	public static String DEFAULT_DATEFORMAT = "yyyy-MM-dd H:m:s";

	private static GsonBuilder builder;

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
		} else {
			if (string.indexOf(':') > 0) {
				format = DEFAULT_DATEFORMAT;
			} else {
				format = "yyyy-MM-dd";
			}
			string = string.replaceAll("/", "-");
			try {
				return new SimpleDateFormat(format).parse(string);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 * 从字符串中去除 HTML标记
	 * 
	 * @public
	 * @param {String} html
	 * @return text
	 */
	public static String stripTags(Object subject) {
		if (null == subject) {
			return "";
		}
		String source = subject.toString();
		return source.replaceAll("\\<.*?>", "");
	}

	/**
	 * @param subject
	 * @return
	 */
	public static String toJSON(Object subject) {
		Gson gson = builder.create();
		return gson.toJson(subject);
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String toLocateDate(Date date, String format) {
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
	 * 截取字符串
	 * 
	 * @public
	 * @param {String} target 原始字符串
	 * @param {int} maxLength 要截取最大长度
	 * @param {String} tail 追加字符串
	 * @return string
	 */
	public static String truncate(Object subject, int maxLength, String tail) {
		String target = subject.toString();
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

	/**
	 * @param anyone
	 */
	public void init(Object anything) {
		builder = new GsonBuilder();

		builder.enableComplexMapKeySerialization();
		builder.serializeNulls();
		builder.setDateFormat(DEFAULT_DATEFORMAT);
		builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
		builder.setVersion(1.0);
	}
}
