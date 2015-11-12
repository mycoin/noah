package org.ionnic.config.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.config.ErrorModel;
import org.ionnic.config.util.JsonUtils;
import org.ionnic.config.util.ServletUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 * 
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(ExceptionResolver.class);

	private String errorView = "/common/error";

	private int statusCode = 500;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		if (obj == null || ex == null) {
			return null;
		}

		ModelAndView mv = null;
		try {
			ErrorModel errorModel = (ErrorModel) request.getAttribute(ErrorModel.ERROR_MODEL_KEY);
			mv = new ModelAndView();

			if (errorModel == null) {
				errorModel = new ErrorModel(500, "Internal Server Error");
				errorModel.setException(ex);
			}

			if (ServletUtils.isJsonMethod((HandlerMethod) obj)) {
				String json = JsonUtils.toJson(errorModel);

				response.setStatus(200);
				response.addHeader("Content-Type", "application/json; charset=UTF-8");
				response.getOutputStream().write(json.getBytes());
			} else {
				response.setStatus(statusCode);
				errorModel.extractTo(mv);
				mv.setViewName(errorView);
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
