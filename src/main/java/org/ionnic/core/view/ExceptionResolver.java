package org.ionnic.core.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author apple
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {

	public static final String DEFAULT_STATUS_ATTRIBUTE = "status";

	// error status code
	private String statusAttribute = DEFAULT_STATUS_ATTRIBUTE;

	// @Override
	// protected ModelAndView doResolveException(HttpServletRequest request,
	// HttpServletResponse response, Object handler, Exception ex) {
	//
	// // Expose ModelAndView for chosen error view.
	// String viewName = determineViewName(ex, request);
	// if (viewName != null) {
	// // Apply HTTP status code for error views, if specified.
	// // Only apply it if we're processing a top-level request.
	// Integer statusCode = determineStatusCode(request, viewName);
	// if (statusCode != null) {
	// applyStatusCodeIfPossible(request, response, statusCode);
	// }
	// return getModelAndView(viewName, ex, request);
	// } else {
	// return null;
	// }
	// }

	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		ModelAndView mv = super.getModelAndView(viewName, ex, request);
		mv.addObject(statusAttribute, 500);

		return mv;
	}

	/**
	 * @return the statusAttribute
	 */
	public String getStatusAttribute() {
		return statusAttribute;
	}

	/**
	 * @param statusAttribute
	 *            the statusAttribute to set
	 */
	public void setStatusAttribute(String statusAttribute) {
		this.statusAttribute = statusAttribute;
	}
}
