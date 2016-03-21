package org.ionnic.common.support.view.tool;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.config.ConfigConstants;
import org.springframework.web.servlet.View;

public class MappingJacksonJsonView implements View, ConfigConstants {

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public void render( Map<String, ?> model, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        response.setContentType("application/javascript; charset=utf-8");
        response.getWriter().write("{}");
    }
}
