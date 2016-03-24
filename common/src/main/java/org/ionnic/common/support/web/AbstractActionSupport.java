package org.ionnic.common.support.web;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.support.DefaultWebException;
import org.ionnic.common.util.WebUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public abstract class AbstractActionSupport implements ActionSupport {

    /**
     * Check the given request
     *
     * @param request
     * @param method
     * @return
     * @throws DefaultWebException
     * @throws HttpMediaTypeNotAcceptableException
     */
    public boolean checkRequest( HttpServletRequest request, HandlerMethod method ) throws DefaultWebException, HttpMediaTypeNotAcceptableException {
        return true;
    }

    /**
     * Just validate X-Csrf-Token.
     *
     * @param request
     * @return
     * @throws DefaultWebException
     */
    public boolean checkSessionBasedToken( HttpServletRequest request ) throws DefaultWebException {
        try {
            return WebUtils.checkRequestSessionToken(request);
        } catch (Exception e) {

        }
        return false;
    }
}
