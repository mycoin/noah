package org.ionnic.common.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

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
    public static String renderTemplate(String content, Map<String, Object> data) {
        StringWriter writer = new StringWriter();
        VelocityEngine engine = new VelocityEngine();

        if (engine.evaluate(new VelocityContext(data), writer, "PageTool.eval()", content)) {
            return writer.toString();
        }

        return null;
    }
}
