package net.breakidea.app.common.view;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.breakidea.common.ConfigConstants;
import net.breakidea.common.util.JsonUtils;

import org.apache.velocity.util.StringUtils;

/**
 * @author apple
 *
 */
public class StringTool extends StringUtils {
    /**
     * HTML编码
     *
     * @param {String} source
     * @return
     */
    public static String encodeHtml( Object subject ) {
        if (null == subject) {
            return "";
        } else {
            String source = subject.toString();

            source = source.replaceAll("\\&", "&amp;");
            source = source.replaceAll("\\\"", "&quot;");
            source = source.replaceAll("\\<", "&lt;");
            source = source.replaceAll("\\>", "&gt;");

            return source;
        }
    }

    /**
     * @param subject
     * @return
     */
    public static String encodeUnicode( Object subject ) {
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
     * @param {String} string
     * @return string
     */
    public static String encodeUrl( Object subject ) {
        String string = trim(subject);
        String decoded = null;
        try {
            decoded = URLEncoder.encode(string, ConfigConstants.CHARSET);
            return decoded.replaceAll("\\+", "%20");
        } catch (Exception e) {

        }
        return string;
    }

    /**
     * @param s
     * @return
     */
    public static String encrypt( String s ) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);

            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 在普通JS环境需要将影响JS语法环境的字符串转义
     *
     * @public
     * @param {String} source 原始字符串
     * @return string
     */
    public static String escapeJs( Object subject ) {
        if (null == subject) {
            return "";
        } else {
            StringBuffer buffer = new StringBuffer();
            String source = subject.toString();
            int length = source.length();

            for (int i = 0; i < length; i++) {
                char c = source.charAt(i);
                switch (c) {
                    case 39:
                        buffer.append("\\'");
                        break;
                    case 34:
                        buffer.append("\\\"");
                        break;
                    case 47:
                        buffer.append("\\/");
                        break;
                    case 92:
                        buffer.append("\\\\");
                        break;
                    case 13:
                        buffer.append("\\r");
                        break;
                    case 10:
                        buffer.append("\\n");
                        break;
                    default:
                        buffer.append(c);
                        break;
                }
            }
            return buffer.toString();
        }
    }

    /**
     * @param json
     * @return
     */
    public static Map<String, Object> parseJson( String json ) {
        try {
            return JsonUtils.parse(json);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param subject
     * @return
     */
    public static String raw( Object subject ) {
        if (subject == null) {
            return "";
        } else {
            return subject.toString();
        }
    }

    /**
     * Removes HTML tags from the string value of the specified object and
     * returns the resulting string.
     * @param obj
     */
    public static String stripTags( Object obj ) {
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
     * @param obj
     * @param allowedTags An array of allowed tag names (i.e. "h1","br","img")
     */
    public static String stripTags( Object obj, List<String> allowedTags ) {
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

    /**
     * @param source
     * @return
     */
    public static String toJson( Object source ) {
        try {
            return JsonUtils.stringify(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * trim 字符串函数
     *
     * @public
     * @param {String} source
     * @return
     */
    public static String trim( Object source ) {
        if (null == source) {
            return "";
        }
        return (source + "").trim();
    }

    /**
     * Limits the string value of 'truncateMe' to 'maxLength' characters.
     * If the string gets curtailed, the configured suffix
     * (default is "...") is used as the ending of the truncated string.
     *
     * @param maxLength An int with the maximum length.
     * @param truncateMe The value to be truncated.
     * @return A String.
     */
    public static String truncate( Object truncateMe, int maxLength ) {
        return truncate(truncateMe, maxLength, "");
    }

    /**
     * Limits the string value of 'truncateMe' to the specified max length in
     * characters. If the string gets curtailed, the specified suffix is used as
     * the ending of the truncated string.
     *
     * @param truncateMe The value to be truncated.
     * @param maxLength An int with the maximum length.
     * @param suffix A String.
     * @return A String.
     */
    public static String truncate( Object truncateMe, int maxLength, String suffix ) {
        return truncate(truncateMe, maxLength, suffix, true);
    }

    /**
     * Limits the string value of 'truncateMe' to the latest complete word
     * within the specified maxLength. If the string gets curtailed, the
     * specified suffix is used as the ending of the truncated string.
     *
     * @param truncateMe The value to be truncated.
     * @param maxLength An int with the maximum length.
     * @param suffix A String.
     * @param defaultTruncateAtWord Truncate at a word boundary if true.
     * @return A String.
     */
    public static String truncate( Object truncateMe, int maxLength, String suffix, boolean defaultTruncateAtWord ) {
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
     * @param input
     * @return
     */
    public static String trimLeft( String input ) {
        int i = 0;
        while (i < input.length() && Character.isWhitespace(input.charAt(i))) {
            i++;
        }
        return input.substring(i);
    }

    /**
     * @param input
     * @return
     */
    public static String trimRight( String input ) {
        int i = input.length() - 1;
        while (i >= 0 && Character.isWhitespace(input.charAt(i))) {
            i--;
        }
        return input.substring(0, i + 1);
    }
}
