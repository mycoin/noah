package net.breakidea.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.breakidea.common.ConfigConstants;
import net.breakidea.common.web.ActionSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ProcessingInterceptor extends HandlerInterceptorAdapter implements ConfigConstants, Ordered {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
        if (!response.isCommitted()) {
            if (response.getContentType() == null) {
                if (log.isDebugEnabled()) {
                    log.debug("ContentType is not defined, setContentType to " + CHARSET);
                }
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
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object bean = handlerMethod.getBean();

            if (bean instanceof ActionSupport) {
                ActionSupport actionBean = (ActionSupport) bean;
                if (log.isDebugEnabled()) {
                    log.debug("Mapped handler is typeof ActionSupport.class, invoke the checkRequest()");
                }
                if (false == actionBean.checkRequest(request, handlerMethod)) {
                    throw new WebException(403, "Access Denied");
                }
            }
        }

        return true;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}