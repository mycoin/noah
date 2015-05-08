package org.ionnic.core;

import org.springframework.web.servlet.ModelAndView;

/**
 * Utils for dealing with Spring MVC
 * 
 * @author
 * 
 */
public class Output {

	/**
	 * The model name to use when returning a ModelAndView with a single model.
	 * object.
	 */
	public static final String RESPONSE_ATTRIBUTE = "data";

	public static ModelAndView getOutputModel(Object obj) {
		return getOutputModel("", obj);
	}

	public static ModelAndView getOutputModel(String viewName, Object obj) {
		return new ModelAndView(viewName, RESPONSE_ATTRIBUTE, obj);
	}

	public static ModelAndView jsp() {
		return null;
	}
}
