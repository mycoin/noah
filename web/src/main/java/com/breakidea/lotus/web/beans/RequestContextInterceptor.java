package com.breakidea.lotus.web.beans;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestContextInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_REQUEST_ID = "request_id";

    private String requestId = DEFAULT_REQUEST_ID;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        if (request.getAttribute(requestId) == null) {
            request.setAttribute(requestId, UUID.randomUUID());
        }
        return true;
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
        if (modelAndView == null) {
            return;
        }

        modelAndView.addObject(requestId, request.getAttribute(requestId));
    }

    /**
     * @param requestId
     */
    public void setRequestId( String requestId ) {
        this.requestId = requestId;
    }
}