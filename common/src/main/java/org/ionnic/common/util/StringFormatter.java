package org.ionnic.common.util;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

    private static final Pattern SPACE_HOLDER_PATTERN = Pattern.compile("\\{[^{}]*\\}");

    private static String extractKeyToken(String group) {
        return group.substring(1, group.length() - 1);
    }

    /**
     * @param pattern
     * @param parameterMap
     * @return
     */
    public static String format(String pattern, Map<String, Object> parameterMap) {

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
            Object value = parameterMap.get(extractKeyToken(group));
            if (value != null) {
                buffer.append(value);
            }
            start = matcher.end();
        } while (matcher.find());

        buffer.append(pattern, start, pattern.length());
        return buffer.toString();
    }

}