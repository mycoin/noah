package org.ionnic.core.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * @author apple
 */
public class MappingExceptionResolver extends AbstractHandlerExceptionResolver {

	private String exceptionAttribute;

	private String defaultErrorView;

	private String defaultStatusCode;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView(defaultErrorView);
		mv.addObject(exceptionAttribute, ex);
		mv.addObject("status", defaultStatusCode);

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
	public String getDefaultStatusCode() {
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
	public void setDefaultStatusCode(String defaultStatusCode) {
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
