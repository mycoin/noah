package com.breakidea.noah.shared.exception;

public class CascadingRuntimeException extends RuntimeException implements CascadingThrowable {

	private static final long serialVersionUID = 1L;

	private final String name;

	public CascadingRuntimeException(final String name, final String message, final Throwable throwable) {
		super(message);
		this.name = name;
	}

	public CascadingRuntimeException(final String message, final Throwable throwable) {
		this(null, message, throwable);
	}

	@Override
	public String getName() {
		return name;
	}

}
