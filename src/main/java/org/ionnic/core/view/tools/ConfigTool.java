package org.ionnic.core.view.tools;

import java.io.IOException;
import java.util.Properties;

import org.ionnic.core.ContextSupport;
import org.ionnic.core.bean.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class ConfigTool {

	private Logger logger = LoggerFactory.getLogger(ConfigTool.class);

	private static Properties config = new Properties();;

	/**
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return get(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String get(String key, String defaultValue) {
		return config.getProperty(key, defaultValue);
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws IOException
	 * @throws Exception
	 */
	public void init(Object context) throws IOException {
		ViewConfig viewConfig = ContextSupport.getBean(ViewConfig.class);
		config.clear();

		if (viewConfig.getConfigPath() != null) {
			for (Resource resource : viewConfig.getConfigPath()) {
				config.load(resource.getInputStream());
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("ConfigTool.init() invoked.");
		}
	}

	@Override
	public String toString() {
		return StringTool.toJSON(config);
	}

}
