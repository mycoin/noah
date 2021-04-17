package com.breakidea.noah.common.utility;

public class Result {

	Object data;

	int status = 0;

	String statusInfo;

	public Object getData() {
		return data;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

}
