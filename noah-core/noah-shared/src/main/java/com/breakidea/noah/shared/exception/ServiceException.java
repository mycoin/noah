package com.breakidea.noah.shared.exception;

public class ServiceException extends CascadingRuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String name, Throwable throwable) {
		super(name, throwable);
	}

	@Override
	public Integer getCode() {
		return 500;
	}
}
