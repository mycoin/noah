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

	private static ViewConfig viewConfig;

	private Properties data = new Properties();;

	/**
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return config(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String config(String key, String defaultValue) {
		return data.getProperty(key, defaultValue);
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws IOException
	 * @throws Exception
	 */
	public void init(Object context) throws IOException {
		if (null == viewConfig) {
			viewConfig = ContextSupport.getBean(ViewConfig.class);

			if (viewConfig.getConfigPath() != null) {
				for (Resource resource : viewConfig.getConfigPath()) {
					data.load(resource.getInputStream());
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("PageUtils.init() invoked.");
		}
	}

	@Override
	public String toString() {
		return "";
	}

}
