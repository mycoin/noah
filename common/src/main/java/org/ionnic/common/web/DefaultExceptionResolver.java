package org.ionnic.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.util.WebUtils;
import org.ionnic.common.view.JsonView;
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
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * @author apple
 *
 */
public class DefaultExceptionResolver implements HandlerExceptionResolver {

    protected final Log logger = LogFactory.getLog(getClass());

    private String errorView = "error";

    private boolean showErrorState = true;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex == null) {
            return null;
        }

        ModelAndView mv;
        try {

            int statusCode = 500;

            // Is the ajax request
            if (WebUtils.hasResponseAnnotation(handler)) {
                mv = new ModelAndView(new JsonView());
                if (showErrorState) {
                    response.sendError(500);
                }
            } else {
                mv = new ModelAndView(errorView);
                response.sendError(500);
            }
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
                }
            } catch (Exception e) {

            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", ex.getMessage());
            data.put("type", ex.getClass().getSimpleName());

            mv.addObject("status", statusCode);
            mv.addObject("data", data);
            mv.addObject("statusInfo", ex.getLocalizedMessage());

            return mv;

        } catch (Exception e) {

        }

        return null;
    }

    /**
     * @param errorView the errorView to set
     */
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    /**
     * @param showErrorState the showErrorState to set
     */
    public void setShowErrorState(boolean showErrorState) {
        this.showErrorState = showErrorState;
    }

}
