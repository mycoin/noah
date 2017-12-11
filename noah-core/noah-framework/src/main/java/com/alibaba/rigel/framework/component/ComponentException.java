package com.alibaba.rigel.framework.component;

import com.alibaba.rigel.shared.exception.CascadingThrowable;

public class ComponentException extends Exception implements CascadingThrowable {

	private static final long serialVersionUID = 1L;

	private final String name;

	public ComponentException(final String name, final String message) {
		this(name, message, null);
	}

	public ComponentException(final String name, final String message, final Throwable throwable) {
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
