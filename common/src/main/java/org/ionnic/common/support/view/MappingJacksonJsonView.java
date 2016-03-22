package org.ionnic.common.support.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.util.GsonUtils;
import org.springframework.web.servlet.View;

public class MappingJacksonJsonView implements View, ConfigConstants {

    private static View instance;

    /**
     * @return
     */
    public static View getInstance() {
        if (instance == null) {
            instance = new MappingJacksonJsonView();
        }
        return instance;
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public void render( Map<String, ?> model, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        String result = "{\"throwable\": true}";
        try {
            result = GsonUtils.toJson(model);
        } catch (Throwable e) {
        }
        response.setContentType("application/javascript; charset=utf-8");
        response.getWriter().write(result);
    }
}
