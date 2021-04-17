package com.breakidea.noah.model;

import java.util.Map;

public class Model {

	private Map<String, Object> featureMap;

	private Integer status;

	private String message;

	public Map<String, Object> getFeatureMap() {
		return featureMap;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setFeatureMap(Map<String, Object> featureMap) {
		this.featureMap = featureMap;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
