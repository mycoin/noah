package org.ionnic.app.util;

import java.util.HashMap;
import java.util.Map;

import org.ionnic.config.util.JsonUtils;
import org.springframework.util.Assert;

/**
 * @author apple
 * 
 */
public class OutputModel {

	private int status = 0;

	private String statusInfo;

	private Map<String, Object> data;

	/**
	 * @param statusCode
	 * @param errorMessage
	 */
	public OutputModel(int statusCode, Exception ex) {
		this(statusCode, ex.getMessage());
		data.put("exception", ex);
	}

	/**
	 * @param statusCode
	 * @param errorMessage
	 */
	public OutputModel(int statusCode, String errorMessage) {
		this(statusCode, errorMessage, null);
		Assert.isTrue(statusCode > 0, "For an error request, `statusCode` must large than 0");
	}

	/**
	 * @param errorMessage
	 */
	public OutputModel(int status, String statusInfo, Map<String, Object> data) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}

		this.status = status;
		this.statusInfo = statusInfo;
		this.data = data;
	}

	/**
	 * @param errorMessage
	 */
	public OutputModel(Map<String, Object> data) {
		this(0, "OK", data);
	}

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
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
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param key
	 * @param value
	 */
	public void assign(Map<String, Object> item) {
		data.putAll(item);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void assign(String key, Object value) {
		data.put(key, value);
	}

	/**
	 * @param statusInfo
	 *            the statusInfo to set
	 */
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	/**
	 * @return
	 */
	public String toJSON() {
		return JsonUtils.toJson(data);
	}
}
