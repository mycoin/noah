package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.support.WebException;
import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public class HttpDispatcherServlet extends DispatcherServlet {

    @Override
    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new WebException(404, "Page Not Found");
    }
}
