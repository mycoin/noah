package org.ionnic.common.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.support.view.MappingJacksonJsonView;
import org.ionnic.common.util.WebUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
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
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver, Ordered, ConfigConstants {

    protected final Log log = LogFactory.getLog(getClass());

    private final String ERROR_VIEW = "common/error";

    private int statusCode = 500;

    private final String NO_HANDLE = "No adapter for handler";

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param status
     * @param ex
     * @return
     */
    protected String getStatusInfo( int status, Exception ex ) {
        String message = ex.getMessage();
        return message;
    }

    @Override
    public ModelAndView resolveException( HttpServletRequest request, HttpServletResponse response, Object handler, final Exception ex ) {
        if (ex == null) {
            return null;
        }

        ModelAndView mv = new ModelAndView();
        DefaultWebException error = null;

        if (!(ex instanceof DefaultWebException)) {

            if (ex instanceof NoSuchRequestHandlingMethodException) {
                error = new DefaultWebException(404, "Page Not Found");
            } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                error = new DefaultWebException(405, "Method Not Allowed");
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                error = new DefaultWebException(415, "Unsupported Media Type");
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                error = new DefaultWebException(406, "Not Acceptable");
            } else if (ex instanceof MissingServletRequestParameterException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof ServletRequestBindingException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof ConversionNotSupportedException) {
                error = new DefaultWebException(500, "Internal Server Error");
            } else if (ex instanceof TypeMismatchException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof HttpMessageNotReadableException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof HttpMessageNotWritableException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof MethodArgumentNotValidException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof MissingServletRequestPartException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof BindException) {
                error = new DefaultWebException(400, "Bad Request");
            } else if (ex instanceof NoHandlerFoundException) {
                error = new DefaultWebException(404, "Page Not Found");
            } else if (ex instanceof ServletException) {
                if (ex.getMessage().startsWith(NO_HANDLE)) {
                    error = new DefaultWebException(404, "Page Not Found");
                }
            }
            if (error == null) {
                error = new DefaultWebException(500, "Internal Server Error", ex);
            }
            log.info("Coutched Error:", ex);
        } else {
            error = (DefaultWebException) ex;
        }

        if (WebUtils.hasAnnotation(handler)) {
            mv.setView(MappingJacksonJsonView.getInstance());
            error.responseTo(mv, null);
        } else {
            mv.setViewName(ERROR_VIEW);
            error.responseTo(mv, response);
        }
        return mv;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode( int statusCode ) {
        this.statusCode = statusCode;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}