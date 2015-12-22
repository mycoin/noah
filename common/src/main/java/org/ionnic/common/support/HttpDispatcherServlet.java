package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        new ServiceException(404, "Page Not Found");
    }

    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        int statusCode = 0;
        boolean found = true;

        try {
            if (ex instanceof NoSuchRequestHandlingMethodException) {
                statusCode = HttpServletResponse.SC_NOT_FOUND;
            } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
            } else if (ex instanceof MissingServletRequestParameterException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof ServletRequestBindingException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof ConversionNotSupportedException) {
                statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            } else if (ex instanceof TypeMismatchException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof HttpMessageNotReadableException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof HttpMessageNotWritableException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof MethodArgumentNotValidException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof MissingServletRequestPartException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof BindException) {
                statusCode = HttpServletResponse.SC_BAD_REQUEST;
            } else if (ex instanceof NoHandlerFoundException) {
                statusCode = HttpServletResponse.SC_NOT_FOUND;
            } else {
                found = false;
            }

            if (found) {
                ServiceException error = new ServiceException(statusCode, "Forbidden");
                error.setException(ex);

                ex = error;
            }
        } catch (Exception e) {

        }
        return super.processHandlerException(request, response, handler, ex);
    }
}
