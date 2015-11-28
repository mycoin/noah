package org.ionnic.common.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.ErrorModel;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("serial")
public class HttpDispatcherServlet extends DispatcherServlet {

    @Override
    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new ErrorModel(request, 404, "Page Not Found");
        throw new ServletException();
    }

    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

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

    @Override
    protected void render(ModelAndView mv, HttpServletRequest resuest, HttpServletResponse response) throws Exception {
        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=UTF-8");
        }
        super.render(mv, resuest, response);
    }
}
