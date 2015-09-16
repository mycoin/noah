package org.ionnic.core.util;

import java.util.Properties;

import org.ionnic.core.GlobalConfig;
import org.ionnic.core.bean.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtils {

	private Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

	private static GlobalConfig globalConfig = GlobalConfig.getInstance();

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
		Properties p = globalConfig.getViewMap();
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
			viewConfig = globalConfig.getViewConfig();
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
