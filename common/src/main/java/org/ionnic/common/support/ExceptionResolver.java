package org.ionnic.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.ErrorModel;
import org.ionnic.common.util.JsonUtils;
import org.ionnic.common.util.ServletUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static Log logger = LogFactory.getLog(ExceptionResolver.class);

    private static ExceptionResolver instance = null;

    private String errorView = "/common/error";

    /**
     * JSON request, default statusCode is 200
     */
    private int statusCode = 200;

    public ExceptionResolver() {
        super();
        instance = this;
    }

    /**
     * @return
     */
    public static ExceptionResolver getInstance() {
        return instance;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        if (ex == null) {
            return null;
        }

        ModelAndView mv = null;
        try {
            ErrorModel errorModel = (ErrorModel) request.getAttribute(ErrorModel.ERROR_MODEL_KEY);

            if (errorModel == null) {
                errorModel = new ErrorModel(request, 500, ex.getMessage());
                errorModel.setException(ex);
            }

            if (ServletUtils.isJSONResponse(obj)) {
                response.setStatus(statusCode);
                response.addHeader("Content-Type", "application/json; charset=UTF-8");
                response.getOutputStream().write(JsonUtils.toJson(errorModel).getBytes());
                response.getOutputStream().close();

                return null;
            } else {
                response.setStatus(errorModel.getStatus());
                mv = new ModelAndView(errorView);
                errorModel.extractTo(mv);
            }
        } catch (Exception e) {
            logger.error("Not catch exception by exceptionResolver:", ex);
        }

        return mv;
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
     *            the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
