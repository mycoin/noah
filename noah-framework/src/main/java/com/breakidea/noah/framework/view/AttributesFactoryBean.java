package com.breakidea.noah.framework.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@SuppressWarnings("rawtypes")
public class AttributesFactoryBean extends AbstractFactoryBean<Map> {

	private Resource configLocation = null;

	@Override
	protected Map<String, ?> createInstance() throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Properties configProperties = new Properties();

		PropertiesLoaderUtils.fillProperties(configProperties, configLocation);
		ClassLoader classLoader = getClass().getClassLoader();

		for (Object keyName : configProperties.keySet()) {
			String attributeName = (String) keyName;
			Class target = classLoader.loadClass(configProperties.getProperty(attributeName));

			returnMap.put(attributeName, target);
		}

		return returnMap;
	}

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}
}
