package org.ionnic.common.support;

import org.ionnic.common.support.view.JsonView;
import org.springframework.core.MethodParameter;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;

/**
 * @author apple
 *
 */
public class JsonResultMethodProcessor implements HandlerMethodReturnValueHandler {

    private View view = new JsonView();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return ResultMap.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {
        ResultMap result = (ResultMap) returnValue;
        ModelMap data = mavContainer.getModel();

        mavContainer.setView(view);
        data.clear();

        data.addAttribute("status", result.getStatus());
        data.addAttribute("data", result.getData());
        data.addAttribute("statusInfo", result.getStatusInfo());
    }
}
