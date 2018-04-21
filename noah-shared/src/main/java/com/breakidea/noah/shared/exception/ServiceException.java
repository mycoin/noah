package com.breakidea.noah.shared.exception;

public class ServiceException extends CascadingRuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String name, String message, Throwable throwable) {
		super(name, message, throwable);
	}
}
