package com.breakidea.noah.framework.component;

import com.breakidea.noah.shared.exception.CascadingRuntimeException;

public class ComponentException extends CascadingRuntimeException {

	private static final long serialVersionUID = 1L;

	public ComponentException(String message, Throwable excecption) {
		super(message, excecption);
	}
}
