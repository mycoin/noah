package org.ionnic.common.util;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
public abstract class TemplateUtils {

    private static final Pattern SPACE_HOLDER_PATTERN = Pattern.compile("\\{[^{}]*\\}");

    /**
     * @param pattern
     * @param parameterMap
     * @return
     */
    public static String formatString(String pattern, Map<String, Object> parameterMap) {

        if (parameterMap == null) {
            parameterMap = Collections.emptyMap();
        }

        Matcher matcher = SPACE_HOLDER_PATTERN.matcher(pattern);
        if (!matcher.find()) {
            return pattern;
        }
        int start = 0;
        StringBuilder buffer = new StringBuilder();
        do {
            buffer.append(pattern, start, matcher.start());
            String group = matcher.group();
            String key = group.substring(1, group.length() - 1);
            Object value = parameterMap.get(key);
            if (value != null) {
                buffer.append(value);
            }
            start = matcher.end();
        } while (matcher.find());

        buffer.append(pattern, start, pattern.length());
        return buffer.toString();
    }

    /**
     * Creates an array containing the literal text from the macro
     * arguments(s) (including the macro's name as the first arg).
     *
     * @param node The parse node from which to grow the argument
     * list.  It's expected to include the block node tree (for the
     * macro body).
     * @return array of arguments
     */
    public static String[] getArgArray(Node node) {

        int count = node.jjtGetNumChildren();
        String[] param = new String[count];

        for (int i = 0; i < count; i++) {
            SimpleNode item = (SimpleNode) node.jjtGetChild(i);
            String string = item.getFirstToken().image;

            if (item.getType() == 8) {
                param[i] = string.substring(1, string.length() - 1);
            } else {
                param[i] = string;
            }
        }

        return param;
    }

    /**
     * @param node
     * @return
     */
    public static String getFirstArg(Node node) {
        String[] args = getArgArray(node);
        if (args.length > 0) {
            if (StringUtils.hasLength(args[0])) {
                return args[0];
            }
        }
        return null;
    }
}
