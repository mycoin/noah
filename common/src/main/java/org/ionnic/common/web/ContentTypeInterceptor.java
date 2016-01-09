package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.HttpException;
import org.ionnic.common.support.Security;
import org.ionnic.common.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("ContentTypeInterceptor.afterCompletion()");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=UTF-8");
        }

        response.addHeader("X-Frame-Options", "deny");
        response.addHeader("X-XSS-Protection", "1; mode=block");
        response.addHeader("X-UA-Compatible", "IE=Edge,chrome=1");

        System.out.println("ContentTypeInterceptor.postHandle()");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String newLocale = request.getParameter("locate");
        if (newLocale != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
        }

        // If it is an ajax request, a csrfToken is required.
        if (WebUtils.isAjax(request) || WebUtils.hasResponseAnnotation(handler)) {
            if (!Security.checkToken(request)) {
                throw new HttpException(403, "Unacceptable Token");
            }
        }

        System.out.println("ContentTypeInterceptor.preHandle()");
        return true;
    }
}
