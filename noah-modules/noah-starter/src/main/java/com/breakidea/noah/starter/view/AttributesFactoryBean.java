package com.breakidea.noah.starter.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@SuppressWarnings("rawtypes")
public class AttributesFactoryBean extends AbstractFactoryBean<Map> {

	private static final String MAPPER_LOCATE = "com/breakidea/noah/starter/view/AttributesMapper.properties";

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	protected Map<String, ?> createInstance() throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Properties configProperties = PropertiesLoaderUtils.loadAllProperties(MAPPER_LOCATE);
		ClassLoader classLoader = getClass().getClassLoader();

		for (Object keyName : configProperties.keySet()) {
			String attributeName = (String) keyName;
			Class target = classLoader.loadClass(configProperties.getProperty(attributeName));

			returnMap.put(attributeName, target);
		}

		return returnMap;
	}
}
