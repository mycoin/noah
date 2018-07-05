package com.breakidea.noah.framework.context;

import com.breakidea.noah.shared.exception.CascadingRuntimeException;

public class ContextException extends CascadingRuntimeException {

	private static final long serialVersionUID = 1L;

	public ContextException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ContextException(String message) {
		super(message, null);
	}
}
