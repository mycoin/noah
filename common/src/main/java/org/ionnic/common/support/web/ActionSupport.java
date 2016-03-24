package org.ionnic.common.support.web;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.support.DefaultWebException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public interface ActionSupport extends ConfigConstants {

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
