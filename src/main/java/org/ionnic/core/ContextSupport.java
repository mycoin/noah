package org.ionnic.core;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author apple
 * 
 */
public class ContextSupport {

	/**
	 * @param name
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return getContext().getBean(requiredType);
	}

	/**
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getContext().getBean(name);
	}

	/**
	 * @return
	 */
	public static WebApplicationContext getContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}

	/**
	 * @return
	 */
	public static ServletContext getServletContext() {
		return getContext().getServletContext();
	}
}
