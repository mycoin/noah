package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=UTF-8");
        }

        response.addHeader("X-Frame-Options", "deny");
        response.addHeader("X-XSS-Protection", "1; mode=block");
        response.addHeader("X-UA-Compatible", "IE=Edge,chrome=1");
    }
}
