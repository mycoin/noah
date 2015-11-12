package org.ionnic.config.view;import java.net.URLEncoder;import java.security.MessageDigest;import org.ionnic.config.support.AppConfig;import org.ionnic.config.util.JsonUtils;import org.springframework.util.StringUtils;/** * @author apple *  */public class StringTool extends StringUtils {    /**     * @param subject     * @return     */    public static String encodeUnicode(Object subject) {        if (null == subject) {            return "";        } else {            String source = subject.toString();            String result = "";            for (int i = 0; i < source.length(); i++) {                int chr = source.charAt(i);                if (chr >= 19968 && chr <= 171941) {                    result += "\\u" + Integer.toHexString(chr);                } else {                    result += source.charAt(i);                }            }            return result;        }    }    /**     * HTML编码     *      * @param {String} source     * @return     */    public static String escapeInH(Object subject) {        if (null == subject) {            return "";        } else {            String source = subject.toString();            source = source.replaceAll("\\&", "&amp;");            source = source.replaceAll("\\\"", "&quot;");            source = source.replaceAll("\\<", "&lt;");            source = source.replaceAll("\\>", "&gt;");            return source;        }    }    /**     * 在普通JS环境需要将影响JS语法环境的字符串转义     *      * @public     * @param {String} source 原始字符串     * @return string     */    public static String escapeInJ(Object subject) {        if (null == subject) {            return "";        } else {            StringBuffer buffer = new StringBuffer();            String source = subject.toString();            int length = source.length();            for (int i = 0; i < length; i++) {                char c = source.charAt(i);                switch (c) {                case 39:                    buffer.append("\\'");                    break;                case 34:                    buffer.append("\\\"");                    break;                case 47:                    buffer.append("\\/");                    break;                case 92:                    buffer.append("\\\\");                    break;                case 13:                    buffer.append("\\r");                    break;                case 10:                    buffer.append("\\n");                    break;                default:                    buffer.append(c);                    break;                }            }            return buffer.toString();        }    }    /**     * 编码 URL 字符串     *      * @param {String} string     * @return string     */    public static String escapeInU(Object subject) {        String string = trim(subject);        try {            return URLEncoder.encode(string, AppConfig.CHARSET);        } catch (Exception e) {        }        return string;    }    /**     * @author LoveCrystal     * @qq 185390516     * @param string     *            s     * @return md5     */    public static String md5(String s) {        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };        try {            byte[] strTemp = s.getBytes();            MessageDigest mdTemp = MessageDigest.getInstance("MD5");            mdTemp.update(strTemp);            byte[] md = mdTemp.digest();            int j = md.length;            char str[] = new char[j * 2];            int k = 0;            for (int i = 0; i < j; i++) {                byte byte0 = md[i];                str[k++] = hexDigits[byte0 >>> 4 & 0xf];                str[k++] = hexDigits[byte0 & 0xf];            }            return new String(str);        } catch (Exception e) {            return "";        }    }    /**     * 从字符串中去除 HTML标记     *      * @public     * @param {String} html     * @return text     */    public static String stripTags(Object subject) {        if (null == subject) {            return "";        }        String source = subject.toString();        return source.replaceAll("\\<.*?>", "");    }    /**     * @param source     * @return     */    public static String toJSON(Object source) {        if (source == null) {            return "{}";        }        try {            return JsonUtils.toJson(source);        } catch (Exception e) {            return null;        }    }    /**     * trim 字符串函数     *      * @public     * @param {String} source     * @return     */    public static String trim(Object source) {        if (null == source) {            source = "";        }        return (source + "").trim();    }}