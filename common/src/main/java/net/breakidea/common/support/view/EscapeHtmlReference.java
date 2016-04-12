package net.breakidea.common.support.view;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;

/**
 * @author apple
 *
 * HTML转义输出
 */
public class EscapeHtmlReference implements ReferenceInsertionEventHandler {

    private static String PAGE_CONTROL = Viewport.CONTEXT_NAME + ".";

    private static String[] escapeVers = new String[] { "util.", PAGE_CONTROL, "lang.", "raw_", "_content" };

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

    /**
     * @param string
     * @return
     */
    private static Object escapeHtml( Object value ) {
        if (null == value) {
            return "";
        } else {
            String source = value.toString();

            source = source.replaceAll("\\&", "&amp;");
            source = source.replaceAll("\\\"", "&quot;");
            source = source.replaceAll("\\<", "&lt;");
            source = source.replaceAll("\\>", "&gt;");

            return source;
        }
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
        return escapeHtml(value);
    }
}