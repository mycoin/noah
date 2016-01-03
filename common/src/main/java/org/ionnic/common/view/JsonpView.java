package org.ionnic.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.util.JsonUtils;
import org.springframework.util.StringUtils;

public class JsonpView extends JsonView {

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String callback = request.getParameter("callback");
        String var = request.getParameter("var");
        String text;

        if (StringUtils.hasLength(callback)) {
            text = callback + "(" + JsonUtils.toJson(model) + ");";
        } else if (StringUtils.hasLength(var)) {
            text = var + " = " + JsonUtils.toJson(model) + ";";
        } else {
            text = "throw 'Bad Request';";
        }

        response.setContentType("text/javascript");
        response.getOutputStream().write(text.getBytes());
    }
}