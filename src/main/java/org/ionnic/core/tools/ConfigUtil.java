package org.ionnic.core.tools;

import java.util.Properties;

import org.ionnic.core.Config;
import org.ionnic.core.bean.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {

	private Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	private static Config config = Config.getInstance();

	private static ViewConfig viewConfig;

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
		Properties p = config.getViewMap();
		return p.getProperty(key, defaultValue);
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object context) {

		if (null == viewConfig) {
			viewConfig = config.getViewConfig();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("PageUtil.init() invoked.");
		}
	}

	@Override
	public String toString() {
		return "";
	}

}
