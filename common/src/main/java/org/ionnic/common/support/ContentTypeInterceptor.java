package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.Config;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

        if (response.getContentType() == null) {
            response.setContentType(Config.CONTENT_TYPE);
            response.setCharacterEncoding(Config.CHARSET);
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "36000000");
    }

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(Config.CHARSET);
        }

//        String newTheme = request.getParameter("theme");
//        if (newTheme != null) {
//            ThemeResolver themeResolver = RequestContextUtils.getThemeResolver(request);
//            if (themeResolver == null) {
//                throw new IllegalStateException("No ThemeResolver found: not in a DispatcherServlet request?");
//            }
//            themeResolver.setThemeName(request, response, newTheme);
//        }

        return true;
    }
}
