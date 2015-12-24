package org.ionnic.common.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

/**
 * @author apple
 *
 */
public abstract class TemplateUtils {

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

}
