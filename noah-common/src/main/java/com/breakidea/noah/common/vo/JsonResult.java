package com.breakidea.noah.common.vo;

import java.io.Serializable;

public class JsonResult<T extends Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status = 0;

	private T data = null;

	private String statusInfo = null;

	public T getData() {
		return data;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
}
