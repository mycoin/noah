package com.breakidea.lotus.shared.support;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 */
	public ServiceException(String string) {
		super(string);
	}
}
