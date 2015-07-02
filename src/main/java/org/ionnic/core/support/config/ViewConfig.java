package org.ionnic.core.support.config;

import org.springframework.core.io.Resource;

public class ViewConfig {

	private Resource[] viewConfig;

	private String encoding;

	private Resource externalPath;

	public String getEncoding() {
		return encoding;
	}

	public Resource[] getViewConfig() {
		return viewConfig;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setViewConfig(Resource[] viewConfig) {
		this.viewConfig = viewConfig;
	}

	public Resource getExternalPath() {
		return externalPath;
	}

	public void setExternalPath(Resource externalPath) {
		this.externalPath = externalPath;
	}
}
