package com.alibaba.rigel.shared.param;

import java.io.Serializable;

public abstract class BaseParam implements Serializable {

	private static final long serialVersionUID = -9051640495523602870L;

	private String namespace = null;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
