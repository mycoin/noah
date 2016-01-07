package org.ionnic.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.HttpException;
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

    private int statusCode = 0;

    HashMap<Integer, String> statusMap;

    public DefaultExceptionResolver() {
        statusMap = new HashMap<Integer, String>();

        statusMap.put(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");
        statusMap.put(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Allowed");
        statusMap.put(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
        statusMap.put(HttpServletResponse.SC_NOT_ACCEPTABLE, "Not Acceptable");
        statusMap.put(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
        statusMap.put(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        statusMap.put(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex == null) {
            return null;
        }

        ModelAndView mv = new ModelAndView();
        int status = 503;

        try {
            if (ex instanceof HttpException) {
                HttpException he = (HttpException) ex;
                status = he.getStatus();

                mv.addObject("status", status);
                mv.addObject("statusInfo", he.getStatusInfo());
                mv.addObject("data", he.getData());
            } else {
                if (ex instanceof NoSuchRequestHandlingMethodException) {
                    status = HttpServletResponse.SC_NOT_FOUND;
                } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                    status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
                } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                    status = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
                } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                    status = HttpServletResponse.SC_NOT_ACCEPTABLE;
                } else if (ex instanceof MissingServletRequestParameterException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof ServletRequestBindingException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof ConversionNotSupportedException) {
                    status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                } else if (ex instanceof TypeMismatchException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof HttpMessageNotReadableException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof HttpMessageNotWritableException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof MethodArgumentNotValidException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof MissingServletRequestPartException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof BindException) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                } else if (ex instanceof NoHandlerFoundException) {
                    status = HttpServletResponse.SC_NOT_FOUND;
                }

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("type", status);
                data.put("message", ex.getLocalizedMessage());

                mv.addObject("status", status);
                mv.addObject("statusInfo", statusMap.get(status));
                mv.addObject("data", data);
            }

            if (WebUtils.hasResponseAnnotation(handler) || handler == null) {
                mv.setView(new JsonView());
                if (statusCode > 0) {
                    response.setStatus(status);
                }
            } else {
                mv.setViewName(errorView);
                response.setStatus(status);
            }
            return mv;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param status
     * @param ex
     * @return
     */
    protected String getStatusInfo(int status, Exception ex) {
        String message = ex.getMessage();

        return message;
    }

    /**
     * @param errorView the errorView to set
     */
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
