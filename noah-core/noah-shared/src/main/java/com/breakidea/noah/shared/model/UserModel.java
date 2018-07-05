package com.breakidea.noah.shared.model;

public class UserModel extends GenaralModel {

	private static final long serialVersionUID = 1L;

	private String password;

	private String status;

	private String userName;

	public String getPassword() {
		return password;
	}

	public String getStatus() {
		return status;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
