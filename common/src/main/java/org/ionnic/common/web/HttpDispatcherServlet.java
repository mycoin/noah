package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.WebException;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author apple
 *
 */
public class HttpDispatcherServlet extends DispatcherServlet {

    private static final long serialVersionUID = -9144342126789693112L;

    @Override
    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new WebException(404, "Page Not Found");
    }
}
