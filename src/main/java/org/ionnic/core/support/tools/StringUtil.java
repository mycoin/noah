package org.ionnic.core.support.tools;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author apple
 */
public class StringUtil {

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
	 * html解码
	 * 
	 * @public
	 * @param source
	 * @return
	 */
	public static String decode(String source) {
		if (null == source) {
			source = "";
		}
		source = source.replaceAll("\\&quot;", "\"");
		source = source.replaceAll("\\&lt;", "<");
		source = source.replaceAll("\\&gt;", ">");
		source = source.replaceAll("\\&amp;", "&");

		return source;
	}

	/**
	 * HTML编码
	 * 
	 * @param {String} source
	 * @return
	 */
	public static String encode(String source) {
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
	 * 编码 URL 字符串
	 * 
	 * @param {String} string
	 * @return string
	 */
	public static String escape(String string) {
		if (isNull(string)) {
			return "";
		}
		try {
			return URLEncoder.encode(string, "utf8");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 转义HTML字符串输出
	 * 
	 * @public
	 * @param {String} target 原始字符串
	 * @return string
	 */
	public static String escapeInH(String source) {
		return encode(source);
	}

	/**
	 * 在普通JS环境需要将影响JS语法环境的字符串转义
	 * 
	 * @public
	 * @param {String} source 原始字符串
	 * @return string
	 */
	public static String escapeInJ(String source) {
		if (null == source) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			int length = source.length();

			for (int i = 0; i < length; i++) {
				char c = source.charAt(i);
				switch (c) {
				case 39: // 单引号
					sb.append("\\'");
					break;
				case 34: // 双引号
					sb.append("\\\"");
					break;
				case 47: // 正斜杠
					sb.append("\\/");
					break;
				case 92: // 反斜杠
					sb.append("\\\\");
					break;
				case 13: // 换行符
					sb.append("\\r");
					break;
				case 10: // 回车符
					sb.append("\\n");
					break;
				default:
					sb.append(c);
					break;
				}
			}
			return sb.toString();
		}

	}

	/**
	 * get GUID
	 * 
	 * @return
	 */
	public static String getGuid() {
		return UUID.randomUUID().toString();
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
		return isNull(source);
	}

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 * 
	 * @public
	 * @param {String} string
	 * @return
	 */
	public static boolean isNull(String string) {
		if (string != null && !string.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 正则表达式的验证函数
	 * 
	 * @public
	 * @param {String} source 被验证的字符串
	 * @param {String} pattern 正则表达式
	 * @return
	 */
	public static boolean isMatchPattern(String source, String pattern) {

		boolean result = false;
		if (source != null && source.length() > 0) {
			Matcher match = Pattern.compile(pattern).matcher(source);
			if (match.find()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 测试函数用例
	 * 
	 * @public
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String src = null;
		String ok = null;

		// escapeInH
		src = "<script>alert(\"安全测试\");</script>";
		ok = "&lt;script&gt;alert(&quot;安全测试&quot;);&lt;/script&gt;";
		declare(ok.equals(escapeInH(src)), "escapeInH error!");

		// escapeInJ
		src = "<h1>\r\n安全测试\"\'</h1><\\>";
		ok = "<h1>\\r\\n安全测试\\\"\\'<\\/h1><\\\\>";
		declare(ok.equals(escapeInJ(src)), "escapeInH error!");

		// isBlank
		declare(isBlank("\t \r\n \b"), "isBlank error!");

		// isEmpty
		declare(isNull(""), "isEmpty error!");
		declare(isNull(null), "isEmpty error!");
		declare(!isNull("not empty"), "isEmpty error!");

		// isMatchPattern
		src = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		declare(isMatchPattern("mycoin@icloud.com", src), "isMatchPattern error!");

		// md5j
		src = "moni-j";
		ok = "8c3fd15aacdc38f72e35e8c9240ca903";
		declare(ok.equals(md5(src)), "md5 error!");

		// replaceAll
		src = "";

		// stripTags
		src = "这是<a href='http:///'>测试用例链接</a>";
		ok = "这是测试用例链接";
		declare(ok.equals(stripTags(src)), "stripTags error!");

		// trim
		src = " \b\t\n\r\n -测试 \n\t    ";
		declare(trim(src).length() == 3, "trim error!");

		// truncate
		src = "现在<em>阿迪达斯</em>三叶草中文网站";
		ok = "现在<em>阿迪达斯";
		declare(ok.equals(truncate(src, 10)), "truncate error!");

		// encodeURL,decodeURL
		src = "http://local:8080/?name=百度#hash";
		ok = "http%3A%2F%2Flocal%3A8080%2F%3Fname%3D%E7%99%BE%E5%BA%A6%23hash";
		declare(ok.equals(escape(src)), "encodeURL error!");
		declare(src.equals(unescape(ok)), "decodeURL error!");

		System.out.println("OK");
	}

	/**
	 * 简单的MD5加密函数
	 * 
	 * @param {Stirng} string
	 * @return
	 */
	public static String md5(String string) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = string.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strTemp);
			byte[] digest = md.digest();
			int j = digest.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = digest[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 字符串替换函数
	 * 
	 * @public
	 * @param {String} source 目标字符串
	 * @param {String} pattern 正则源码
	 * @param {String} dest 替换值
	 * @return string result
	 */
	public static String replaceAll(String source, String pattern, String dest) {
		Matcher match = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(source);
		source = match.replaceAll(dest);
		return source;
	}

	/**
	 * 从字符串中去除 HTML标记
	 * 
	 * @public
	 * @param {String} html
	 * @return text
	 */
	public static String stripTags(String html) {
		Pattern regx = Pattern
				.compile("</?\\p{Alpha}+ *( +\\p{Alpha}+ *=(\"(\\\\.|[\\x00-\\x21\\x23-\\x5b\\u005d-\\uffff])*\"|'(\\\\.|[\\x00-\\x26\\x28-\\x5b\\u005d-\\uffff])*'|[\\x00-\\x3d\\u003f-\\uffff]*) *)*>");
		Matcher matches = regx.matcher(html);
		return matches.replaceAll("");
	}

	/**
	 * trim 字符串函数
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static String trim(String source) {
		if (null == source) {
			source = "";
		}
		return source.trim();
	}

	/**
	 * 截取字符串
	 * 
	 * @public
	 * @param {String} target 原始字符串
	 * @param {int} maxLength 要截取最大长度
	 * @return string
	 */
	public static String truncate(String target, int maxLength) {
		return truncate(target, maxLength, "");
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
				// 截取标签
				tag = target.substring(i, end + 1);
				int n = tag.length();
				if (tag.endsWith("/>")) {
					isTag = true;
				} else if (tag.startsWith("</")) { // 结束符
					name = tag.substring(2, end - i);
					int size = tags.size() - 1;
					// 堆栈取出HTML开始标签
					if (size >= 0 && name.equals(tags.get(size))) {
						isTag = true;
						tags.remove(size);
					}
				} else { // 开始符
					spanEnd = tag.indexOf(' ', 0);
					spanEnd = spanEnd > 0 ? spanEnd : n;
					name = tag.substring(1, spanEnd);
					if (name.trim().length() > 0) {
						// 如果有结束符则为HTML标签
						spanEnd = target.indexOf("</" + name + ">", end);
						if (spanEnd > 0) {
							isTag = true;
							tags.add(name);
						}
					}
				}
				// 非HTML标签字符
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
			} else { // 非HTML标签字符
				maxLength--;
				result += c;
			}
		}
		// 添加未结束的HTML标签
		for (String endTag : tags) {
			result += "</" + endTag + ">";
		}
		if (i < length) {
			result += (tail == null ? "" : tail);
		}
		return result;
	}

	/**
	 * 解码已编码的URL字符串
	 * 
	 * @param {String} string
	 * @return string
	 */
	public static String unescape(String source) {
		if (source == null || ("").equals(source.trim())) {
			return "";
		}
		try {
			return URLDecoder.decode(source, "utf8");
		} catch (Exception e) {
			return "";
		}
	}
}
