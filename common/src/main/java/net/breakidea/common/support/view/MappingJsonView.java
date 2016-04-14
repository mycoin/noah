package net.breakidea.common.support.view;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.breakidea.common.ConfigConstants;
import net.breakidea.common.util.JsonUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author apple
 *
 */
public class MappingJsonView extends AbstractView implements ConfigConstants {

    private final Log log = LogFactory.getLog(getClass());

    /**
     * Default content type: "application/json".
     * Overridable through {@link #setContentType}.
     */
    public static final String CONTENT_TYPE = "application/json";

    /**
     * Default content type for JSONP: "text/javascript".
     */
    public static final String JSONP_CONTENT_TYPE = "text/javascript";

    /**
     * Pattern for validating jsonp callback parameter values.
     */
    private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[A-Za-z][0-9A-Za-z_\\.]*");

    private boolean disableCaching = true;

    private String[] jsonpParameterNames = new String[] { "callback" };

    private static View main;

    /**
     * @return
     */
    public static View getInstance() {
        if (main == null) {
            main = new MappingJsonView();
        }
        return main;
    }

    /**
     * @param model
     * @return
     */
    private String getJsonResultText( Map<String, Object> model ) {
        String responseText = null;
        try {
            responseText = JsonUtils.stringify(model);
        } catch (Exception e) {
            Map<String, Object> errorMap = new HashMap<String, Object>();

            errorMap.put(STATUS_INFO_NAME, "Unsupported Model");
            errorMap.put(STATUS_NAME, 500);
            errorMap.put(DATA_NAME, e.getClass().getName());

            log.error("Unsupported Json Model.", e);
            try {
                responseText = JsonUtils.stringify(errorMap);
            } catch (Exception ex) {
                responseText = "{}";
            }
        } finally {

        }
        return responseText;
    }

    /**
     * @param request
     * @return
     */
    private String getJsonpParameterValue( HttpServletRequest request ) {
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
    protected void prepareResponse( HttpServletRequest request, HttpServletResponse response ) {
        setResponseContentType(request, response);
        response.setCharacterEncoding(CHARSET);
        if (this.disableCaching) {
            response.addHeader("Cache-Viewport", "no-store");
        }
    }

    @Override
    protected void renderMergedOutputModel( Map<String, Object> model, HttpServletRequest request, HttpServletResponse response ) throws Exception {

        String jsonpParameterValue = getJsonpParameterValue(request);
        ServletOutputStream out = response.getOutputStream();
        String responseText = getJsonResultText(model);

        if (null == jsonpParameterValue) {
            response.setContentType(CONTENT_TYPE);
            out.write(responseText.getBytes());
        } else {
            response.setContentType(JSONP_CONTENT_TYPE);
            out.write((jsonpParameterValue + "(" + responseText + ");").getBytes());
        }
    }
}