package net.breakidea.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.breakidea.common.config.ConfigConstants;
import net.breakidea.common.web.ActionSupport;

import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ProcessingInterceptor extends HandlerInterceptorAdapter implements ConfigConstants, Ordered {

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
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object bean = handlerMethod.getBean();

            if (bean instanceof ActionSupport) {
                ActionSupport actionBean = (ActionSupport) bean;
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