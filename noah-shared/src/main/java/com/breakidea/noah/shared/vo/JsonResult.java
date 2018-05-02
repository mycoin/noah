package com.breakidea.noah.shared.vo;

import java.io.Serializable;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status = 0;

	private Object data = null;

	private String statusInfo = null;

	public Object getData() {
		return data;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
}
