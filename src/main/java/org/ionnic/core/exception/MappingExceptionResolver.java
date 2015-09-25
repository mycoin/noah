package org.ionnic.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * @author apple
 */
public class MappingExceptionResolver extends AbstractHandlerExceptionResolver {

	private String exceptionAttribute;

	private String defaultErrorView;

	private int defaultStatusCode;

	private static Logger logger = LoggerFactory.getLogger(MappingExceptionResolver.class);

	@SuppressWarnings("deprecation")
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView(defaultErrorView);
		mv.addObject(exceptionAttribute, ex.getMessage());
		mv.addObject("status", 500);

		logger.error("resolved exception: ", ex);
		response.setStatus(defaultStatusCode, ex.getLocalizedMessage());

		return mv;
	}

	/**
	 * @return the defaultErrorView
	 */
	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	/**
	 * @return the defaultStatusCode
	 */
	public int getDefaultStatusCode() {
		return defaultStatusCode;
	}

	/**
	 * @return the exceptionAttribute
	 */
	public String getExceptionAttribute() {
		return exceptionAttribute;
	}

	/**
	 * @param defaultErrorView
	 *            the defaultErrorView to set
	 */
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	/**
	 * @param defaultStatusCode
	 *            the defaultStatusCode to set
	 */
	public void setDefaultStatusCode(int defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

	/**
	 * @param exceptionAttribute
	 *            the exceptionAttribute to set
	 */
	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}
}
