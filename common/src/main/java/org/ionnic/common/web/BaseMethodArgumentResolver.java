package org.ionnic.common.web;

import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.support.WebContext;
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
    public boolean supportsParameter(MethodParameter parameter) {
        Type paramType = parameter.getGenericParameterType();
        if (paramType.equals(WebContext.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        return new WebContext();
    }

}
