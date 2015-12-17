package org.ionnic.common.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.ErrorModel;
import org.ionnic.common.Security;
import org.ionnic.common.util.ServletUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // If it is an ajax request, a csrfToken is required.
        if (ServletUtils.isAjax(request) || ServletUtils.isJSONResponse(handler)) {
            if (!Security.checkToken(request)) {
                new ErrorModel(request, 500, "Unacceptable Token");
                throw new ServletException("Unacceptable Token");
            }
        }

        return true;
    }
}
