package org.ionnic.common.util;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

/**
 * @author apple
 *
 */
public abstract class TemplateUtils {

    private static final Pattern SPACE_HOLDER_PATTERN = Pattern.compile("\\{[^{}]*\\}");

    /**
     * @param content
     * @param data
     * @return
     */
    public static String renderContent(String content, Map<String, Object> data) throws VelocityException {
        StringWriter writer = new StringWriter();
        VelocityEngine engine = new VelocityEngine();

        if (engine.evaluate(new VelocityContext(data), writer, "TemplateUtils.renderTemplate()", content)) {
            return writer.toString();
        }
        return null;
    }

    /**
     * @param content
     * @param data
     * @return
     * @throws VelocityException
     */
    public static String renderTemplate(String content, Map<String, Object> data) throws VelocityException {
        StringWriter writer = new StringWriter();
        VelocityEngine engine = new VelocityEngine();

        Template tpl = engine.getTemplate(content);
        tpl.merge(new VelocityContext(data), writer);

        return writer.toString();
    }

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
}
