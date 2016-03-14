package org.ionnic.common.support.view;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.ionnic.common.support.view.tool.StringTool;

/**
 * @author apple
 *
 * HTML转义输出
 */
public class EscapeHtmlReference implements ReferenceInsertionEventHandler {

    private static String[] escapeVers = new String[] { "util.", "page.", "lang.", "raw_", "_content" };

    private static String BLANK_STRING = "";

    /**
     * @param ref
     * @return
     */
    private static String getVar(String ref) {
        ref = ref.replace("$", BLANK_STRING);
        ref = ref.replace("!", BLANK_STRING);
        ref = ref.replace("{", BLANK_STRING);
        ref = ref.replace("}", BLANK_STRING);
        return ref;
    }

    @Override
    public Object referenceInsert(String reference, Object value) {
        if (value == null || !(value instanceof String)) {
            return value;
        }
        reference = getVar(reference);
        for (int i = 0; i < escapeVers.length; i++) {
            String item = escapeVers[i];
            if (reference.startsWith(item) || reference.endsWith(item)) {
                return value;
            }
        }
        return StringTool.escapeInH(value);
    }
}