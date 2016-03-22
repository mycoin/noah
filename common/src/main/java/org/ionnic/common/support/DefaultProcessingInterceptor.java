package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class DefaultProcessingInterceptor extends HandlerInterceptorAdapter implements ConfigConstants {

    @SuppressWarnings("unused")
    private final Log log = LogFactory.getLog(getClass());

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=" + CHARSET);
            response.setCharacterEncoding(CHARSET);
        }
    }

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(CHARSET);
        }
        return true;
    }
}