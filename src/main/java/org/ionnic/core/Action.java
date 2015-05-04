package org.ionnic.core;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 * 
 */
public abstract class Action {

	ModelAndView modelAndView = new ModelAndView();

	public ModelAndView commit() {
		return null;
	}

	public void addContext(Map<String, Object> map) {
		modelAndView.addAllObjects(map);
	}
}
