package org.ionnic.common.view;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.ionnic.common.view.tool.StringTool;
import org.springframework.util.StringUtils;

/**
 * HTML转义输出
 */
public class EscapeHtmlReference implements ReferenceInsertionEventHandler {

    private static String[] escapeVers = new String[] { "util.", "page.", "body", "lang.", "statusInfo", "raw_" };

    /**
     * @param ref
     * @return
     */
    private static String getVar(String ref) {
        ref = ref.replace("$", "");
        ref = ref.replace("!", "");
        ref = ref.replace("{", "");
        return ref;
    }

    @Override
    public Object referenceInsert(String reference, Object value) {
        if (value == null || !(value instanceof String)) {
            return trimWhitespace(value);
        }
        reference = getVar(reference);
        for (int i = 0; i < escapeVers.length; i++) {
            String item = escapeVers[i];
            if (reference.startsWith(item)) {
                return value;
            }
        }
        return StringTool.escapeInH(value);
    }

    /**
     * @param value
     * @return
     */
    private Object trimWhitespace(Object value) {
        if (null == value) {
            return null;
        } else {
            return StringUtils.trimWhitespace(value.toString());
        }
    }
}