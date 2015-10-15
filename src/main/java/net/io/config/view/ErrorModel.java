package net.io.config.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

public class ErrorModel {

	public static final String ERROR_MODEL_KEY = ErrorModel.class.getName() + ".ERROR_ATTRIBUTE";

	private int status = 500;

	private String statusInfo = "Access Denied";

	private Exception exception = new ServletException();

	/**
	 * @param status
	 * @param statusInfo
	 */
	public ErrorModel(int status, String statusInfo) {
		setStatus(status);
		setStatusInfo(statusInfo);
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @return
	 */
	public Map<String, Object> getModelAsMap() {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", exception);
		result.put("status", status);
		result.put("statusInfo", statusInfo);

		return result;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the statusInfo
	 */
	public String getStatusInfo() {
		return statusInfo;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param statusInfo
	 *            the statusInfo to set
	 */
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
}
