package org.ionnic.common.http;

import org.ionnic.common.support.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {

        JSONObject object = (JSONObject) returnValue;
        ModelMap model = mavContainer.getModel();
        object.toModel(model);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return JSONObject.class.isAssignableFrom(returnType.getParameterType());
    }
}