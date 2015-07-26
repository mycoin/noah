package org.ionnic.core.support.config;

import org.springframework.core.io.Resource;

/**
 * @author apple
 */
public class ViewConfig {

	private Resource[] configPath;

	private Resource externalPath;

	public static final String HTML_EXTENSION = ".html";

	private String suffix = HTML_EXTENSION;

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
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
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
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
