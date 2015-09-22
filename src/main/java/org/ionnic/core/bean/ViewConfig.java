package org.ionnic.core.bean;

import org.springframework.core.io.Resource;

public class ViewConfig {

	private String charset;

	private Resource[] configPath;

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @return the configPath
	 */
	public Resource[] getConfigPath() {
		return configPath;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @param configPath
	 *            the configPath to set
	 */
	public void setConfigPath(Resource[] configPath) {
		this.configPath = configPath;
	}
}
