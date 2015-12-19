package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.util.ServletUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static Log logger = LogFactory.getLog(ExceptionResolver.class);

    private String errorAttribute = "error";

    private String errorView = null;

    private int statusCode = 0;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        ModelAndView mv = null;
        if (ex == null) {
            return null;
        }

        InternalServletException error;
        if (ex instanceof InternalServletException) {
            error = (InternalServletException) ex;
        } else {
            error = new InternalServletException(500, "Internal Server Exception");
            error.setException(ex);
        }
        try {
            if (ServletUtils.isJSONResponse(obj)) {
                response.setContentType("application/json; charset=UTF-8");
                if (statusCode > 0) {
                    response.setStatus(statusCode);
                } else {
                    response.setStatus(error.getObject().getStatus());
                }
                response.getOutputStream().write(error.toString().getBytes());
                response.getOutputStream().close();

                return null;
            } else {
                mv = new ModelAndView(errorView);
                mv.addObject(errorAttribute, error.getObject());
            }
        } catch (Exception e) {
            logger.error("Not catch exception by exceptionResolver:", ex);
        }

        return mv;
    }

    /**
     * @param errorAttribute the errorAttribute to set
     */
    public void setErrorAttribute(String errorAttribute) {
        this.errorAttribute = errorAttribute;
    }

    /**
     * @param errorView
     *            the errorView to set
     */
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
