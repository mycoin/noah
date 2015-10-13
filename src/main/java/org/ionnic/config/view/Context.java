package org.ionnic.config.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ionnic.config.util.GsonUtils;
import org.springframework.util.Assert;

@SuppressWarnings("serial")
public final class Context implements Cloneable, Serializable {

	private String statusInfo = "OK";

	private int status = 0;

	private Object data = new HashMap<String, Object>();

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public Context(int errorCode, String errorMessage) {
		Assert.isTrue(errorCode > 0, "If report an error, errorCode must > 0!");

		status = errorCode;
		statusInfo = errorMessage;
	}

	/**
	 * @param dataResult
	 */
	public Context(Object dataResult) {
		data = dataResult;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
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
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void setData(String key, Object value) {
		Map<String, Object> dataMap = (Map<String, Object>) data;
		if (value == null) {
			dataMap.remove(key);
		} else {
			dataMap.put(key, value);
		}
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

	/**
	 * @param object
	 * @return
	 */
	public String toJSON() {
		return GsonUtils.toJson(this);
	}

	/**
	 * @return
	 */
	public Map<String, Object> asMap() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("status", status);
		dataMap.put("statusInfo", statusInfo);
		dataMap.put("data", data);

		return dataMap;
	}
}
