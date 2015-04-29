package org.ionnic.app.utils;

import org.springframework.web.servlet.ModelAndView;

/**
 * Utils for dealing with Spring MVC
 * 
 * @author ajesler
 * 
 */
public class SpringMVCUtils {

	/**
	 * The model name to use when returning a ModelAndView with a single model.
	 * object.
	 */
	public static final String SINGLE_OBJECT_RESPONSE = "responseObject";

	/**
	 * This is a helper method to deal with the fact that we have to use
	 * ModelAndView return types on our controller methods if we want content
	 * type negotiation to work with Spring 3.1. If we used the more natural
	 * method signature style in our controllers like <br />
	 * {@code public @ResponseBody MyModelObject getModelObject(@PathVariable(value="id") int id)}
	 * <br />
	 * then our content negotiation will stop working because of the @ResponseBody
	 * annotation. A fix to make @ResponseBody respond to the usual content
	 * negotiation methods is coming in Spring 3.2, but that is not released
	 * yet. See https://jira.springsource.org/browse/SPR-7517
	 * 
	 * Although we use a ModelAndView, which holds a set of models, when the
	 * message converter for whatever content type (xml, json, etc) is run, we
	 * only want one object to be outputted. To deal with this, we set the
	 * modelKey property of the xml marshaller bean and the
	 * extractValueFromSingleKeyModel in the json view. This ensures that if
	 * there is only one model object and it has the right name (the constants
	 * SINGLE_OBJECT_RESPONSE value), then only that model will be outputted.
	 * These properties are set in the rest-servlet.xml file.
	 * 
	 * The other option is to use @ResponseBody and ensure that clients specify
	 * the desired content type in the request header. This may not work well
	 * for some browsers as they do odd things like give xml a higher priority
	 * than html. However since we are only make RESTful apis in this project,
	 * that would not concern us.
	 * 
	 * @param obj
	 * @return
	 */
	public static ModelAndView getOutputModel(Object obj) {
		return new ModelAndView("", SINGLE_OBJECT_RESPONSE, obj);
	}
}
