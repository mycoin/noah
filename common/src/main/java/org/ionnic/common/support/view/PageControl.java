package org.ionnic.common.support.view;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.common.config.RuntimeConstants;

/**
 * @author apple
 *
 */
public class PageControl implements RuntimeConstants {

    public static final String SCREEN_KEY = "body";

    public static final String CONTEXT_NAME = "page";

    public static final String TEMPLATE_EXT = ".vm";

    private String layoutName = null;

    private ViewToolContext context = null;

    /**
     * For the default, render the screen first.
     */
    private boolean isRenderLayout = false;

    /**
     * @param context
     */
    public PageControl( ViewToolContext context ) {
        this.context = context;
        this.context.put(CONTEXT_NAME, this);
    }

    /**
     * @param templateName
     * @param dataMap
     */
    public StringWriter renderTpl( String templateName, Map<String, Object> varMap ) {
        StringWriter writer = new StringWriter();

        try {
            if (varMap != null) {
                Map<String, Object> toolbox = context.getToolbox();
                for (String key : toolbox.keySet()) {
                    varMap.put(key, toolbox.get(key));
                }
            }

            VelocityEngine velocityEngine = context.getVelocityEngine();
            velocityEngine.mergeTemplate(templateName, CHARSET, new VelocityContext(varMap), writer);
        } catch (Exception e) {
            writer.write("<!-- BAD RESOURCE: " + templateName + " -->");
        }
        return writer;
    }

    /**
     * @param blockName
     * @param object
     * @return
     */
    public Object getVariable( String key ) {
        return context.get(key);
    }

    /**
     * @param blockName
     * @param object
     * @return
     */
    public Object getVariable( String key, Object defaultValue ) {
        Object var = getVariable(key);
        if (var == null) {
            return defaultValue;
        }
        return null;
    }

    /**
     * @param blockName
     * @param out
     */
    public void setVariable( String key, Object object ) {
        context.put(key, object);
    }

    /**
     * @return
     */
    public String getLayout() {
        return layoutName;
    }

    /**
     * @return
     */
    public String setLayout( String layoutName ) {
        this.layoutName = layoutName + TEMPLATE_EXT;
        return "";
    }

    /**
     * @param body
     */
    public void prepareLayout( StringWriter body ) {
        isRenderLayout = true;
        setVariable(SCREEN_KEY, body);
    }

    /**
     * @return
     */
    public boolean isRenderLayout() {
        return isRenderLayout;
    }

}
