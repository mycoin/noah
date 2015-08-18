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
	 * @return the externalExtension
	 */
	public String getExternalExtension() {
		return externalExtension;
	}

	/**
	 * @return the externalPath
	 */
	public Resource getExternalPath() {
		return externalPath;
	}

	/**
	 * @return the shareTools
	 */
	public boolean isShareTools() {
		return shareTools;
	}

	/**
	 * @param configPath
	 *            the configPath to set
	 */
	public void setConfigPath(Resource[] configPath) {
		this.configPath = configPath;
	}

	/**
	 * @param externalExtension
	 *            the externalExtension to set
	 */
	public void setExternalExtension(String externalExtension) {
		this.externalExtension = externalExtension;
	}

	/**
	 * @param externalPath
	 *            the externalPath to set
	 */
	public void setExternalPath(Resource externalPath) {
		this.externalPath = externalPath;
	}

	/**
	 * @param shareTools
	 *            the shareTools to set
	 */
	public void setShareTools(boolean shareTools) {
		this.shareTools = shareTools;
	}
}
