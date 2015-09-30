package org.ionnic.core.exception;

import javax.servlet.ServletException;

public class ServiceException extends ServletException {

	private static final long serialVersionUID = 4963280676486243080L;

	/**
	 * Create an ServiceException with a specific message and cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * Create an ServiceException with a specific message.
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceException(String message) {
		super(message);
	}

}
