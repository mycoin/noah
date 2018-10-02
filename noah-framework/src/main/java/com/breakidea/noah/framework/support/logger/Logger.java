package com.breakidea.noah.framework.support.logger;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;

public interface Logger extends Log {

	void debug(Object key, Object[] params);

	void debug(Object key, Object[] params, Throwable cause);

	void error(Object key, Object[] params);

	void error(Object key, Object[] params, Throwable cause);

	void fatal(Object key, Object[] params);

	void fatal(Object key, Object[] params, Throwable cause);

	void info(Object key, Object[] params);

	void info(Object key, Object[] params, Throwable cause);

	void trace(Object key, Object[] params);

	void trace(Object key, Object[] params, Throwable cause);

	void warn(Object key, Object[] params);

	void warn(Object key, Object[] params, Throwable cause);
	
	void setResourceBundle(ResourceBundle bundle);
	
	ResourceBundle getResourceBundle();
}