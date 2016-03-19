package org.ionnic.common.support.view;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.Config;
import org.ionnic.common.util.JsonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author apple
 *
 */
public class MappingJacksonJsonView extends AbstractView {

    /**
     * Default content type: "application/json".
     * Overridable through {@link #setContentType}.
     */
    public static final String CONTENT_TYPE = "application/json";

    /**
     * Default content type for JSONP: "application/javascript".
     */
    public static final String JSONP_CONTENT_TYPE = "application/javascript";

    /**
     * Pattern for validating jsonp callback parameter values.
     */
    private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[0-9A-Za-z_\\.]*");

    private boolean disableCaching = true;

    private String[] jsonpParameterNames = new String[] { "callback" };

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jsonpParameterValue = getJsonpParameterValue(request);
        ServletOutputStream out = response.getOutputStream();

        String responseText = JsonUtils.toJson(model);
        if (null == jsonpParameterValue) {
            response.setContentType(CONTENT_TYPE);
            out.write(responseText.getBytes());
        } else {
            response.setContentType(JSONP_CONTENT_TYPE);
            out.write((jsonpParameterValue + "(" + responseText + ");").getBytes());
        }
    }

    /**
     * @param request
     * @return
     */
    private String getJsonpParameterValue(HttpServletRequest request) {
        if (this.jsonpParameterNames != null) {
            for (String name : this.jsonpParameterNames) {
                String value = request.getParameter(name);
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                if (!CALLBACK_PARAM_PATTERN.matcher(value).matches()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Ignoring invalid jsonp parameter value: " + value);
                    }
                    continue;
                }
                return value;
            }
        }
        return null;
    }

    @Override
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        setResponseContentType(request, response);
        response.setCharacterEncoding(Config.CHARSET);
        if (this.disableCaching) {
            response.addHeader("Cache-Control", "no-store");
        }
    }
}
