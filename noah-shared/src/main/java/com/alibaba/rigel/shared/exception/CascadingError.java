package com.alibaba.rigel.shared.exception;

public class CascadingError extends Error implements CascadingThrowable {

	private static final long serialVersionUID = 1L;

	private final String name;

	public CascadingError(final String name, final String message, final Throwable throwable) {
		super(message);
		this.name = name;
	}

	public CascadingError(final String message, final Throwable throwable) {
		this(null, message, throwable);
	}
	
	@Override
	public String getName() {
		return name;
	}
}
