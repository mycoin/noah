package com.breakidea.noah.framework.support.logger;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;

public class LoggerWrapper implements Logger {

	private Log logger;

	private ResourceBundle resourceBundle = null;

	public LoggerWrapper(Log log) {
		this.logger = log;
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void debug(Object key, Object[] params) {
		if (logger.isDebugEnabled()) {
			logger.debug(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void debug(Object key, Object[] params, Throwable cause) {
		if (logger.isDebugEnabled()) {
			logger.debug(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void debug(Object message, Throwable cause) {
		logger.debug(message, cause);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(Object key, Object[] params) {
		if (logger.isErrorEnabled()) {
			logger.error(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void error(Object key, Object[] params, Throwable cause) {
		if (logger.isErrorEnabled()) {
			logger.error(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void error(Object message, Throwable cause) {
		logger.error(message, cause);
	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public void fatal(Object key, Object[] params) {
		if (logger.isFatalEnabled()) {
			logger.fatal(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void fatal(Object key, Object[] params, Throwable cause) {
		if (logger.isFatalEnabled()) {
			logger.fatal(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void fatal(Object message, Throwable cause) {
		logger.fatal(message, cause);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void info(Object key, Object[] params) {
		if (logger.isInfoEnabled()) {
			logger.info(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void info(Object key, Object[] params, Throwable cause) {
		if (logger.isInfoEnabled()) {
			logger.info(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void info(Object message, Throwable cause) {
		logger.info(message, cause);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public String toString() {
		return logger.toString();
	}

	public void trace(Object message) {
		logger.trace(message);
	}

	public void trace(Object key, Object[] params) {
		if (logger.isTraceEnabled()) {
			logger.trace(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void trace(Object key, Object[] params, Throwable cause) {
		if (logger.isTraceEnabled()) {
			logger.trace(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void trace(Object message, Throwable cause) {
		logger.trace(message, cause);
	}

	public void warn(Object message) {
		logger.warn(message);
	}

	public void warn(Object key, Object[] params) {
		if (logger.isWarnEnabled()) {
			logger.warn(LoggerUtil.getMessage(this, key, params));
		}
	}

	public void warn(Object key, Object[] params, Throwable cause) {
		if (logger.isWarnEnabled()) {
			logger.warn(LoggerUtil.getMessage(this, key, params), cause);
		}
	}

	public void warn(Object message, Throwable cause) {
		logger.warn(message, cause);
	}

	@Override
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	@Override
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
}
