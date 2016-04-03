package org.ionnic.common.support.view;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.common.config.RuntimeConstants;
import org.ionnic.common.support.DigestSupport;
import org.ionnic.common.util.ContextUtils;
import org.ionnic.common.util.WebUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author apple
 *
 */
public class Viewport implements RuntimeConstants {

    public static final String BLANK = "";

    public static final String CONTEXT_NAME = "page";

    public static final String SCREEN_KEY = "body";

    public static final String TEMPLATE_EXT = ".vm";

    private static WebApplicationContext application = ContextUtils.getApplicationContext();

    /**
     * @param context
     */
    public Viewport( Context context ) {
        this.context = (ViewToolContext) context;

        setVariable(SCREEN_KEY, 0);
        setVariable(CONTEXT_NAME, this);
    }

    /**
     * @param templateName
     * @return
     */
    public StringWriter renderTpl( String templateName ) {
        return renderTpl(templateName, null);
    }

    /**
     * @param templateName
     * @param varMap
     * @param safeMode
     * @return
     */
    public StringWriter renderTpl( String templateName, Map<String, Object> varMap ) {
        StringWriter writer = new StringWriter();
        try {
            if (!templateName.endsWith(TEMPLATE_EXT)) {
                templateName = templateName.concat(TEMPLATE_EXT);
            }
            if (varMap != null) {
                varMap.putAll(context.getToolbox());
                varMap.put(CONTEXT_NAME, this);
            }

            VelocityContext renderContext = new VelocityContext(varMap);
            VelocityEngine velocityEngine = context.getVelocityEngine();
            velocityEngine.mergeTemplate(templateName, CHARSET, renderContext, writer);
        } catch (Exception e) {
            writer.write("<!-- BAD RESOURCE: [" + templateName + "] -->");
        }

        return writer;
    }

    /**
     * @param data
     * @return
     */
    public static String encrypt( String data ) {
        return DigestSupport.encrypt(data);
    }

    /**
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt( String data, String key ) throws Exception {
        return DigestSupport.DESEncode(data, key);
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
        return var;
    }

    /**
     * @param code
     * @return
     */
    public static String getConfig( String code, String defaultValue ) {
        return application.getMessage(code, null, defaultValue, null);
    }

    /**
     * @param code
     * @return
     */
    public static String getConfig( String code ) {
        return application.getMessage(code, null, null, null);
    }

    /**
     * @return
     */
    public String getLayout() {
        return layoutName;
    }

    /**
     * @param urlName
     * @param param
     * @return
     */
    public String getURI( String urlName ) {
        HttpServletRequest req = context.getRequest();
        if (!urlName.startsWith("/")) {
            urlName = "/" + urlName;
        }
        return req.getContextPath() + urlName;
    }

    /**
     * @return
     */
    public String getToken() {
        HttpServletRequest request = context.getRequest();
        return WebUtils.getSessionToken(request);
    }

    /**
     * @return
     */
    public static String getTokenName() {
        return WebUtils.PARAMETER_NAME;
    }

    /**
     * @return
     */
    public static String getTokenHeader() {
        return WebUtils.HEADER_NAME;
    }

    /**
     * @return
     */
    public static String getGuid() {
        return DigestSupport.getGuid();
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
    public String setLayout( String layoutName ) {
        this.layoutName = layoutName + TEMPLATE_EXT;

        return BLANK;
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

    /**
     * @param templateName
     * @return
     */
    public boolean isTemplateExist( String templateName ) {
        try {
            context.getVelocityEngine().getTemplate(templateName, CHARSET);
            context.hashCode();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String toString() {
        return "Implement:{" + getClass().getName() + "}";
    }

    /**
     * For the default, render the screen first.
     */
    private boolean isRenderLayout = false;

    private String layoutName = null;

    private ViewToolContext context = null;

}
