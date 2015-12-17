package org.ionnic.common.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.ErrorModel;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

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
        if (ex instanceof HttpMediaTypeNotSupportedException
                || ex instanceof NoSuchRequestHandlingMethodException
                || ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof HttpMediaTypeNotSupportedException
                || ex instanceof HttpMediaTypeNotAcceptableException
                || ex instanceof MissingServletRequestParameterException
                || ex instanceof ServletRequestBindingException
                || ex instanceof ConversionNotSupportedException
                || ex instanceof TypeMismatchException
                || ex instanceof HttpMessageNotReadableException
                || ex instanceof HttpMessageNotWritableException
                || ex instanceof MethodArgumentNotValidException
                || ex instanceof MissingServletRequestPartException
                || ex instanceof BindException
                || ex instanceof NoHandlerFoundException
                ) {

            new ErrorModel(request, ex);
            ex = new ServletException();
        }
        return super.processHandlerException(request, response, handler, ex);
    }

    @Override
    protected void render(ModelAndView mv, HttpServletRequest resuest, HttpServletResponse response) throws Exception {
        if (response.getContentType() == null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
        }
        super.render(mv, resuest, response);
    }
}
