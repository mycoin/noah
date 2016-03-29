package org.ionnic.common.support.view.tool;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.RenderTool;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.common.util.WebUtils;

/**
 * @author apple
 *
 */
public class PageTool extends RenderTool {

    private ViewToolContext context;

    private VelocityEngine engine;

    private HttpServletRequest request;

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
     * 请求级别的初始化函数
     *
     * @param obj
     * @throws Exception
     */
    public void init( Object object ) {
        context = (ViewToolContext) object;
        request = context.getRequest();

        // cache it.
        engine = context.getVelocityEngine();
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
                map.putAll(context.getToolbox());
            }
            data = new VelocityContext(map);
            engine.getTemplate(templateName).merge(data, writer);
        } catch (Exception e) {
            writer.write("<!-- ERROR RENDER TEMPLATE -->");
        }
        return writer;
    }
}
