package com.alibaba.rigel.framework.context;

import com.alibaba.rigel.shared.exception.CascadingThrowable;

public class ContextException extends Exception implements CascadingThrowable {

	private static final long serialVersionUID = 1L;

	private final String name;

	public ContextException(final String name, final String message) {
		this(name, message, null);
	}

	public ContextException(final String message) {
		this(null, message, null);
	}

	public ContextException(final String name, final String message, final Throwable throwable) {
		super(message, throwable);
		this.name = name;
	}

	@Override
	public final String getName() {
		return name;
	}

	public String getMessage() {
		if (name == null) {
			return super.getMessage();
		} else {
			return super.getMessage() + " (key [" + name + "])";
		}
	}
}
