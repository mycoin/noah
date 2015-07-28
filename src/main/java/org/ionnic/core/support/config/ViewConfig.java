package org.ionnic.core.support.config;

import org.springframework.core.io.Resource;

/**
 * @author apple
 */
public class ViewConfig {

	private Resource[] configPath;

	private Resource externalPath;

	private String externalExtension = ".html";

	private boolean shareTools;

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
	 * @return the externalExtension
	 */
	public String getExternalExtension() {
		return externalExtension;
	}

	/**
	 * @param externalExtension
	 *            the externalExtension to set
	 */
	public void setExternalExtension(String externalExtension) {
		this.externalExtension = externalExtension;
	}

	/**
	 * @return the shareTools
	 */
	public boolean isShareTools() {
		return shareTools;
	}

	/**
	 * @param shareTools
	 *            the shareTools to set
	 */
	public void setShareTools(boolean shareTools) {
		this.shareTools = shareTools;
	}
}
