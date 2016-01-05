package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.ContextContainer;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author apple
 *
 */
public class BaseMethodArgumentResolver implements HandlerMethodArgumentResolver {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("init ContextContainer()");
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        ContextContainer contextContainer = new ContextContainer(request, response, mavContainer);
        return contextContainer;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ContextContainer.class.isAssignableFrom(parameter.getParameterType());
    }
}
