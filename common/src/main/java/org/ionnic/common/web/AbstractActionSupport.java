package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.support.WebException;
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
     * @throws WebException
     * @throws HttpMediaTypeNotAcceptableException
     */
    public boolean checkRequest( HttpServletRequest request, HandlerMethod method ) throws WebException {
        return true;
    }

    /**
     * Just validate X-Csrf-Token.
     *
     * @param request
     * @return
     * @throws WebException
     */
    public boolean checkSessionToken( HttpServletRequest request ) throws WebException {
        if (!WebUtils.checkSessionToken(request)) {
            throw new WebException(403, "Access Denied");
        }
        return true;
    }
}
