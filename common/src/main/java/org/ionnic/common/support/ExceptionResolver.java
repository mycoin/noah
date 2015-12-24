package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.util.WebUtils;
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

    private boolean showErrorState = false;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        ModelAndView mv = null;
        if (ex == null) {
            return null;
        }

        InternalException error;
        if (ex instanceof InternalException) {
            error = (InternalException) ex;
        } else {
            error = new InternalException(500, "Internal Server Error");
            error.setException(ex);
        }

        JSONObject result = error.getObject();
        try {
            if (WebUtils.hasResponseAnnotation(obj)) {
                response.setContentType("application/json; charset=UTF-8");

                // default all ajax responses 200
                if (showErrorState) {
                    response.setStatus(result.getStatus());
                }
                response.getOutputStream().write(error.toString().getBytes());
                response.getOutputStream().close();
            } else {
                mv = new ModelAndView(errorView);
                mv.addObject(errorAttribute, result);
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
    public void setShowErrorState(boolean showErrorState) {
        this.showErrorState = showErrorState;
    }
}