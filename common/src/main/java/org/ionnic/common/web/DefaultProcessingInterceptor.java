package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class DefaultProcessingInterceptor extends HandlerInterceptorAdapter implements ConfigConstants, Ordered {

    private final Log log = LogFactory.getLog(getClass());

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

        if (!response.isCommitted()) {
            if (response.getContentType() == null) {
                response.setContentType("text/html; charset=" + CHARSET);
                response.setCharacterEncoding(CHARSET);
            }
        }
    }

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(CHARSET);
        }

        if (handler instanceof HandlerMethod) {
            Object bean = ((HandlerMethod) handler).getBean();
            if (bean instanceof ActionSupport) {

                if (log.isDebugEnabled()) {
                    log.debug("The handle is instanceof ActionSupport, invoke checkRequest()");
                }

                if (false == ((ActionSupport) bean).checkRequest(request, (HandlerMethod) handler)) {
                    throw new DefaultWebException(403, "Access Denied");
                }
            }
        }

        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}