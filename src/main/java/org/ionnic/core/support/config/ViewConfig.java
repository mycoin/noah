package org.ionnic.core.support.config;

import org.springframework.core.io.Resource;

public class ViewConfig {

	public static final int AUTO_ESCAPE = 0;

	/**
	 * @return the autoEscape
	 */
	public static int getAutoEscape() {
		return AUTO_ESCAPE;
	}

	private Resource[] configPath;

	private String charset;

	private Resource externalPath;

	private Resource layoutPath;

	private int tripMode = ViewConfig.AUTO_ESCAPE;

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
	 * @return the externalPath
	 */
	public Resource getExternalPath() {
		return externalPath;
	}

	/**
	 * @return the layoutPath
	 */
	public Resource getLayoutPath() {
		return layoutPath;
	}

	/**
	 * @return the tripMode
	 */
	public int getTripMode() {
		return tripMode;
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

	/**
	 * @param externalPath
	 *            the externalPath to set
	 */
	public void setExternalPath(Resource externalPath) {
		this.externalPath = externalPath;
	}

	/**
	 * @param layoutPath
	 *            the layoutPath to set
	 */
	public void setLayoutPath(Resource layoutPath) {
		this.layoutPath = layoutPath;
	}

	/**
	 * @param tripMode
	 *            the tripMode to set
	 */
	public void setTripMode(int tripMode) {
		this.tripMode = tripMode;
	}
}
