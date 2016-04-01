package org.ionnic.common.support.view.helper;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.generic.RenderTool;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.common.config.RuntimeConstants;
import org.ionnic.common.util.WebUtils;

/**
 * @author apple
 *
 */
public class PageO extends RenderTool implements RuntimeConstants {

    private ViewToolContext velocityContext;

    private VelocityEngine velocityEngine;

    private HttpServletRequest request;

    /**
     * the view control name, a global var in every velocity template file
     */
    public static final String CONTROL_NAME = "page";

    private static final String BLOCK_PREFIX = CONTROL_NAME + ":raw";

    /**
     * @param velocityContext
     */
    public PageO( Context context ) {

        // put PageControl to context
        if (context.containsKey(CONTROL_NAME)) {
            throw new VelocityException("Cannot create PageControl, because of an existing model object of the same name: " + CONTROL_NAME);
        } else {
            context.put(CONTROL_NAME, this);
            velocityContext = (ViewToolContext) context;
            request = velocityContext.getRequest();

            // cache it.
            velocityEngine = velocityContext.getVelocityEngine();
        }
    }

    /**
     * @param blockName
     * @return
     */
    public Object getInternal( String key ) {
        return velocityContext.get(BLOCK_PREFIX + key);
    }

    /**
     * @return
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @return
     */
    public String getToken() {
        return WebUtils.getSessionToken(request);
    }

    /**
     * @return
     */
    public String getTokenField() {
        String token = getToken();
        return "<input type=\"hidden\" name=\"" + WebUtils.PARAMETER_NAME + "\" value=\"" + token + "\" />";
    }

    /**
     * @param urlName
     * @param param
     * @return
     */
    public String getUrl( String urlName ) {
        if (!urlName.startsWith("/")) {
            urlName = "/" + urlName;
        }
        return request.getContextPath() + urlName;
    }

    /**
     * @param templateName
     * @return
     */
    public StringWriter render( String templateName ) {
        return render(templateName, null);
    }

    /**
     * 请求渲染模板
     *
     * @param templateName
     * @param map
     * @return
     */
    public StringWriter render( String templateName, Map<String, Object> map ) {
        StringWriter writer = new StringWriter();
        Context data = null;
        try {
            if (map != null) {
                map.putAll(velocityContext.getToolbox());
            }
            data = new VelocityContext(map);
            velocityEngine.getTemplate(templateName).merge(data, writer);
        } catch (Exception e) {
            writer.write("<!-- ERROR RENDER TEMPLATE -->");
        }
        return writer;
    }

    /**
     * @param key
     * @param value
     */
    public void setInternal( String key, Object value ) {
        velocityContext.put(BLOCK_PREFIX + key, value);
    }
}
