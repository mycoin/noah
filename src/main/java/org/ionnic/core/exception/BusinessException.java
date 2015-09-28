package org.ionnic.core.exception;

import javax.servlet.ServletException;

public class BusinessException extends ServletException {

	private static final long serialVersionUID = 4963280676486243080L;

	/**
	 * Create an BusinessException with a specific message and cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public BusinessException(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * Create an BusinessException with a specific message.
	 * 
	 * @param message
	 *            the message
	 */
	public BusinessException(String message) {
		super(message);
	}

}
