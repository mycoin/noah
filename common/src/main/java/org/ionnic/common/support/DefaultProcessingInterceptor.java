package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.support.web.ActionSupport;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class DefaultProcessingInterceptor extends HandlerInterceptorAdapter implements ConfigConstants {

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
                prepareInitRequest(request, (ActionSupport) bean, (HandlerMethod) handler);
            }
        }

        return true;
    }

    /**
     * @param request
     * @param actionBean
     * @param handler
     */
    private void prepareInitRequest( HttpServletRequest request, ActionSupport actionBean, HandlerMethod handler ) {
        if (!actionBean.checkRequest(request, true)) {
            throw new DefaultWebException(403, "Access Denied");
        }

        if (log.isDebugEnabled()) {
            log.debug("The handle is instanceof ActionSupport, invoke checkRequest()");
        }
    }
}