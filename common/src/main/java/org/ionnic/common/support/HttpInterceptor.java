package org.ionnic.common.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.ErrorModel;
import org.ionnic.common.Security;
import org.ionnic.common.util.ServletUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 * 
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    /**
     * @param response
     */
    private void initHeaders(HttpServletResponse response) {
        if (!response.containsHeader("X-XSS-Protection")) {
            response.addHeader("X-Frame-Options", "deny");
            response.addHeader("X-XSS-Protection", "1; mode=block");
            response.addHeader("X-UA-Compatible", "IE=Edge,chrome=1");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=UTF-8");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // init response headers
        initHeaders(response);

        // If it is an ajax request, a csrfToken is required.
        if (ServletUtils.isAjax(request) || ServletUtils.isJSONResponse(handler)) {
            if (!Security.checkToken(request)) {
                ServletException exception = new ServletException("Unacceptable Token");
                new ErrorModel(request, 500, exception);
                throw exception;
            }
        }

        return true;
    }
}
