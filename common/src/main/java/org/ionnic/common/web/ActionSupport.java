package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.config.RuntimeConstants;
import org.ionnic.common.support.DefaultWebException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public interface ActionSupport extends RuntimeConstants {

    /**
     * Check the given request
     *
     * @param request
     * @param method
     * @return
     * @throws DefaultWebException
     * @throws HttpMediaTypeNotAcceptableException
     */
    public boolean checkRequest( HttpServletRequest request, HandlerMethod method ) throws DefaultWebException, HttpMediaTypeNotAcceptableException;
}
