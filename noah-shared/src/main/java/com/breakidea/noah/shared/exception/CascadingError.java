package com.breakidea.noah.shared.exception;

public class CascadingError extends Error implements CascadingThrowable {

	private static final long serialVersionUID = 1L;

	public CascadingError(final String message, final Throwable throwable) {
		super(message);
	}

	@Override
	public Integer getCode() {
		return 500;
	}
}
