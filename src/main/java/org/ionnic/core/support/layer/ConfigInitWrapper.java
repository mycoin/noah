package org.ionnic.core.support.layer;

import org.springframework.core.io.Resource;

public class ConfigInitWrapper {

	private Resource[] viewConfig;

	public Resource[] getViewConfig() {
		return viewConfig;
	}

	public void setViewConfig(Resource[] viewConfig) {
		this.viewConfig = viewConfig;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	private String encoding;
}
