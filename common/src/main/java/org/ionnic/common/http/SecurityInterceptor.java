package org.ionnic.common.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.Security;
import org.ionnic.common.support.ServiceException;
import org.ionnic.common.util.WebUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // If it is an ajax request, a csrfToken is required.
        if (WebUtils.isAjax(request) || WebUtils.hasResponseAnnotation(handler)) {
            if (!Security.checkToken(request)) {
                throw new ServiceException(403, "Unacceptable Token");
            }
        }
        return true;
    }
}
