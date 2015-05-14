/**
 * Copyright 2014 Baidu Inc. All rights reserved.
 *
 * author:  mycoin (nqliujiangtao@gmail.com)
 * date:    Nov 18, 2014 7:14:24 PM
 * file:    StringUtil.java
 */
package org.ionnic.test.misc;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	public static String decodeHTML(String source) {
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
	 * 解码已编码的URL字符串
	 * 
	 * @param {String} string
	 * @return string
	 */
	public static String decodeURL(String source) {
		if (source == null || ("").equals(source.trim())) {
			return "";
		}
		try {
			return URLDecoder.decode(source, "utf8");
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
	public static String encodeHTML(String source) {
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
	 * @param {String} str
	 * @return string
	 */
	public static String encodeURL(String string) {
		if (string == null || ("").equals(string.trim())) {
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
		return encodeHTML(source);
	}

	/**
	 * 在普通JS环境需要将影响JS语法环境的字符串转义
	 * 
	 * @public
	 * @param {String} target 原始字符串
	 * @return string
	 */
	public static String escapeInJ(String source) {
		if (null == source) {
			source = "";
		}

		StringBuffer sb = new StringBuffer();
		int lth = source.length();

		for (int i = 0; i < lth; i++) {
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

	public static String getASCII(String string) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = string.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0XFF));
		}
		return strBuf.toString();
	}

	public static boolean inArray(String source, String[] stringArray) {
		return inArray(stringArray, source);
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean inArray(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否空字符串，无效字符串
	 * 
	 * @public
	 * @param string
	 * @return
	 */
	public static boolean isBlank(String source) {
		return isEmpty(trim(source));
	}

	/**
	 * 验证是否是固定电话
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static boolean isCellphone(String source) {
		// 国际电信联盟(ITU)规定各国和地区代号长度为1-3位
		String regex = "^((\\+)?\\d{1,3}-)?(\\d{3,4}-)?\\d{6,8}$";
		return isMatchPattern(source, regex);
	}

	/**
	 * 判断字符是否是中文
	 * 
	 * @param c
	 *            字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串的格式
	 * 
	 * @param source
	 * @param {boolean} isCN 允许有中文
	 * @param {boolean} isNum 允许有数字
	 * @param {boolean} isLetter 允许有字母
	 * @return
	 */
	public static boolean isContain(String source, boolean isCN, boolean isNum, boolean isLetter) {
		String regexStart = "^[";
		String regexEnd = "]+$";

		if (isCN == true) {
			regexStart = regexStart + "|\u4e00-\u9fa5";
		}
		if (isNum == true) {
			regexStart = regexStart + "|0-9";
		}
		if (isLetter == true) {
			regexStart = regexStart + "|a-zA-Z";
		}
		return isMatchPattern(source, regexStart + regexEnd);
	}

	/**
	 * 判断是否电子邮件地址
	 * 
	 * @public
	 * @param {String} src 源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isEmail(String source) {
		return isMatchPattern(source, "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 * 
	 * @public
	 * @param {String} string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string != null && !string.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否浮点数字表示
	 * 
	 * @public
	 * @param {String} src 源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isFloat(String source) {
		return isMatchPattern(source, "^[+-]?(([1-9]([0-9]+)?)|[0])(.[0-9]+[1-9])?$");
	}

	/**
	 * 验证IP地址
	 * 
	 * @public
	 * @param {String} src 源字符串
	 * @return
	 */
	public static boolean isIP(String source) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
		return isMatchPattern(source, regex);
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
	 * 判断字符串是否是乱码
	 * 
	 * @param strName
	 *            字符串
	 * @return 是否是乱码
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证是否是手机号
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static boolean isMobile(String source) {
		return isMatchPattern(source, "^[1]+[3,5,8]+\\d{9}$");
	}

	/**
	 * isNumeric
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static boolean isNumeric(String source) {
		if (null == source) {
			source = "";
		}
		return source.matches("^[\\-]?[0-9]+$");
	}

	/**
	 * 邮编地址
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static boolean isPostalcode(String source) {
		String regex = "^\\d{6}$";
		return isMatchPattern(source, regex);
	}

	/**
	 * 校验网址
	 * 
	 * @public
	 * @param {String} source
	 * @return
	 */
	public static boolean isUrl(String source) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return isMatchPattern(source, regex);
	}

	/**
	 * 连接字符串函数
	 * 
	 * @param {ArrayList<String>} array 数组片段
	 * @param {String} glue 分隔符
	 * @return
	 */
	public static String join(ArrayList<?> array, String glue) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = array.size(); i < len; i++) {
			sb.append(String.valueOf(array.get(i)));
			if (i < len - 1) {
				sb.append(glue);
			}
		}
		return sb.toString();
	}

	/**
	 * 连接字符串函数
	 * 
	 * @param {ArrayList<String>} array 数组片段
	 * @param {String} glue 分隔符
	 * @return
	 */
	public static String join(Object[] args, String glue) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = args.length; i < len; i++) {
			sb.append(String.valueOf(args[i]));
			if (i < len - 1) {
				sb.append(glue);
			}
		}
		return sb.toString();
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String lowerCaseFirst(String s) {
		if (s == null || Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
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
		declare(isEmpty(""), "isEmpty error!");
		declare(isEmpty(null), "isEmpty error!");
		declare(!isEmpty("not empty"), "isEmpty error!");

		// isFloat
		declare(isFloat("+0.59928888"), "isFloat error!");
		declare(isFloat("-10010"), "isFloat error!");
		declare(!isFloat("0.59928888."), "isFloat error!");
		declare(!isFloat("hao123.com"), "isFloat error!");

		// isEmail
		declare(isEmail("mycoin@icloud.com"), "isEmail error!");
		declare(!isEmail("@icloud.com"), "isEmail error!");

		// isIP
		declare(isIP("10.46.189.32"), "isIP error!");
		declare(!isIP("8.8.8.888"), "isIP error!");

		// isMatchPattern
		src = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		declare(isMatchPattern("mycoin@icloud.com", src), "isMatchPattern error!");

		// isMobile
		declare(isMobile("13299062928"), "isMobile error!");
		declare(!isMobile("10010"), "isMobile error!");

		// isNumeric
		declare(isNumeric("-13299062928"), "isNumeric error!");
		declare(!isNumeric("hao123.com"), "isNumeric error!");

		// isCellphone
		declare(isCellphone("021-888888"), "isCellphone error!");
		declare(isCellphone("+097-120-12345678"), "isCellphone error!");
		declare(!isCellphone("hao123.com"), "isCellphone error!");

		// isUrl
		declare(isUrl("http://www.moni-j.com/user?action=is#hashCode"), "isUrl error!");
		declare(!isUrl("www.hao123.com"), "isUrl error!");

		// join
		ArrayList<String> list = new ArrayList<String>();
		list.add("10010");
		list.add("192.168.1.1");
		declare("10010;192.168.1.1".equals(join(list, ";")), "join Error!");

		// md5
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
		declare(ok.equals(encodeURL(src)), "encodeURL error!");
		declare(src.equals(decodeURL(ok)), "decodeURL error!");

		// wildcard
		declare(wildcard("/WEB-INF/web.xml", "*.xml"), "wildcard() error.");

		// isMessyCode
		declare(isMessyCode("�"), "messyCode error.");

		System.out.println("OK");
		
		
		
		
		System.out.println(getASCII("你"));
	}

	/**
	 * 简单的MD5加密函数
	 * 
	 * @param {Stirng} s
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
			return null;
		}
	}

	/**
	 * Return a context-relative path, beginning with a "/", that represents the
	 * canonical version of the specified path after ".." and "." elements are
	 * resolved out. If the specified path attempts to go outside the boundaries
	 * of the current context (i.e. too many ".." path elements are present),
	 * return <code>null</code> instead.
	 * 
	 * @param path
	 *            Path to be normalized
	 * @return String normalized path
	 */
	public static String normalizePath(String path) {
		// Normalize the slashes and add leading slash if necessary
		String normalized = path;
		if (normalized.indexOf('\\') >= 0) {
			normalized = normalized.replace('\\', '/');
		}

		if (!normalized.startsWith("/")) {
			normalized = "/" + normalized;
		}

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		// Resolve occurrences of "%20" in the normalized path
		while (true) {
			int index = normalized.indexOf("%20");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + " " + normalized.substring(index + 3);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return (null); // Trying to go outside our context
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);
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
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String upperCaseFirst(String data) {
		if (data == null || Character.isUpperCase(data.charAt(0))) {
			return data;
		}
		String firstLetter = data.substring(0, 1).toUpperCase();
		String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}

	/**
	 * 通配符算法。 可以匹配"*"和"?" 如a*b?d可以匹配aAAAbcd
	 * 
	 * @param pattern
	 *            匹配表达式
	 * @param str
	 *            匹配的字符串
	 * @return
	 */
	public static boolean wildcard(String str, String pattern) {
		if (pattern == null || str == null) {
			return true;
		}

		boolean result = false;
		char c; // 当前要匹配的字符串
		boolean before = false; // 是否遇到通配符*
		int backI = 0;// 回溯,当遇到通配符时,匹配不成功则回溯
		int backJ = 0;
		int i, j;
		for (i = 0, j = 0; i < str.length();) {
			if (pattern.length() <= j) {
				if (backI != 0) {// 有通配符,但是匹配未成功,回溯
					before = true;
					i = backI;
					j = backJ;
					backI = 0;
					backJ = 0;
					continue;
				}
				break;
			}

			if ((c = pattern.charAt(j)) == '*') {
				if (j == pattern.length() - 1) {// 通配符已经在末尾,返回true
					result = true;
					break;
				}
				before = true;
				j++;
				continue;
			}

			if (before) {
				if (str.charAt(i) == c) {
					before = false;
					backI = i + 1;
					backJ = j;
					j++;
				}
			} else {
				if (c != '?' && c != str.charAt(i)) {
					result = false;
					if (backI != 0) {// 有通配符,但是匹配未成功,回溯
						before = true;
						i = backI;
						j = backJ;
						backI = 0;
						backJ = 0;
						continue;
					}
					break;
				}
				j++;
			}
			i++;
		}

		if (i == str.length() && j == pattern.length()) {
			// 全部遍历完毕
			result = true;
		}
		return result;
	}
}
