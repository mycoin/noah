package org.ionnic.config.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("serial")
public class HttpDispatcherServlet extends DispatcherServlet {

    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {

        if (ex instanceof HttpRequestMethodNotSupportedException || ex instanceof HttpMediaTypeNotSupportedException) {
            ExceptionResolver er = new ExceptionResolver();
            ModelAndView mv = er.resolveException(request, response, handler, ex);
            if (response.isCommitted()) {
                return null;
            } else {
                return mv;
            }
        } else {
            return super.processHandlerException(request, response, handler, ex);
        }
    }
}