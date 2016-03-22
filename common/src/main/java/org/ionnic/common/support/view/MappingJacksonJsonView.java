package org.ionnic.common.support.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.util.JsonUtils;
import org.springframework.web.servlet.View;

/**
 * @author apple
 *
 */
public class MappingJacksonJsonView implements View, ConfigConstants {

    private static View main;

    private Log log = LogFactory.getLog(getClass());

    /**
     * @return
     */
    public static View getInstance() {
        if (main == null) {
            main = new MappingJacksonJsonView();
        }
        return main;
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public void render( Map<String, ?> model, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        String result = JsonUtils.toJson(model, log);

        response.setContentType("application/json; charset=" + CHARSET);
        response.getWriter().write(result);
    }
}
