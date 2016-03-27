package org.ionnic.common.support;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.ionnic.common.support.view.tool.StringTool;

/**
 * @author apple
 *
 * HTML转义输出
 */
public class EscapeReferenceEventHandler implements ReferenceInsertionEventHandler {

    private static String[] escapeVers = new String[] { "util.", "page.", "lang.", "raw_", "_content" };

    /**
     * @param ref
     * @return
     */
    private static String getVar( String ref ) {
        ref = ref.replace("$", "");
        ref = ref.replace("!", "");
        ref = ref.replace("{", "");
        ref = ref.replace("}", "");
        return ref;
    }

    @Override
    public Object referenceInsert( String reference, Object value ) {
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
        return StringTool.encodeHtml(value.toString());
    }
}