package org.ionnic.core.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 * 
 */
public abstract class Action {

	ModelAndView modelAndView = new ModelAndView();
	public static Logger log = LoggerFactory.getLogger(Action.class);

	public void addContext(Map<String, Object> map) {
		modelAndView.addAllObjects(map);
	}

	public ModelAndView commit() {
		return null;
	}
}
