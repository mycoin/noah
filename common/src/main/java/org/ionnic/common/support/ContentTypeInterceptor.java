package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.Config;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (response.getContentType() == null) {
            response.setContentType(Config.CONTENT_TYPE);
            response.setCharacterEncoding(Config.CHARSET);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(Config.CHARSET);
        }

        String session = request.getSession(true).getId();
        System.out.println(session);
        return true;
    }
}
