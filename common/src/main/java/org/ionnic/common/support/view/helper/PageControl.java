package org.ionnic.common.support.view.helper;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.common.config.RuntimeConstants;
import org.ionnic.common.support.view.EnhancedVelocityView;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
public class PageControl extends StringTool implements RuntimeConstants {

    public static final String PAGE_CONTROL = "page";

    public static final String BODY_NAME = "body";

    public static final String LAYOUT_FLAG = PageControl.class.getName();

    private static final String BLANK_STRING = "";

    private String layout = null;

    private ViewToolContext velocityContext;

    /**
     * @param enhancedVelocityView
     * @param context
     * @param velocityEngine
     */
    public PageControl( EnhancedVelocityView view, ViewToolContext context ) {

        if (context.containsKey(PAGE_CONTROL)) {
            throw new VelocityException("Cannot create PageControl, because of an existing model object of the same name: " + PAGE_CONTROL);
        } else {
            context.put(PAGE_CONTROL, this);
            velocityContext = (ViewToolContext) context;
        }
    }

    /**
     * @param key
     * @return
     */
    public Object get( String key ) {
        return get(key, "");
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get( String key, Object defaultValue ) {
        Object value = velocityContext.get(key);
        if (value instanceof String) {
            return ((String) value).trim();
        } else {
            return value == null ? defaultValue : value;
        }
    }

    /**
     * @return
     */
    public String getLayout() {
        return layout;
    }

    /**
     * @param velocityView
     * @return
     */
    public StringWriter renderTemplate( String templateName, Map<String, Object> varMap ) {
        StringWriter writer = new StringWriter();
        Context context = createContext(varMap);

        try {
            VelocityEngine velocityEngine = velocityContext.getVelocityEngine();
            velocityEngine.mergeTemplate(templateName, CHARSET, context, writer);
        } catch (Exception e) {

        }
        return writer;
    }

    /**
     * @param contextMap
     */
    private VelocityContext createContext( Map<String, Object> varMap ) {
        VelocityContext context = new VelocityContext(varMap);

        Map<String, Object> toolbox = velocityContext.getToolbox();
        for (String key : toolbox.keySet()) {
            context.put(key, toolbox.get(key));
        }

        return context;
    }

    /**
     * @param key
     * @param content
     */
    public void set( String key, Object content ) {
        velocityContext.put(key, content);
    }

    /**
     * @param layoutUrl
     */
    public String setLayout( String layoutUrl ) {
        if (StringUtils.hasLength(layoutUrl)) {
            layout = layoutUrl + ".vm";
        } else {
            layout = null;
        }
        return BLANK_STRING;
    }

    /**
     * @param mark
     * @return
     * @return
     */

    public boolean prepareLayout( StringWriter body ) {
        String flagLayout = LAYOUT_FLAG + ".vm";
        if (body == null) {
            return flagLayout.equals(getLayout());
        } else {
            setLayout(LAYOUT_FLAG);
            set(BODY_NAME, body);

            return false;
        }
    }
}
