package com.breakidea.noah.web.support.velocity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.breakidea.noah.framework.util.JsonUtils;

public class StaticAttributesFactoryBean extends AbstractFactoryBean<Map<String, Object>> implements InitializingBean {

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	protected Map<String, Object> createInstance() throws Exception {
		Map<String, Object> toolContext = new HashMap<String, Object>();

		toolContext.put("NumberUtils", NumberUtils.class);
		toolContext.put("StringUtils", StringUtils.class);
		toolContext.put("ObjectUtils", ObjectUtils.class);
		toolContext.put("JsonUtils", JsonUtils.class);

		return toolContext;
	}
}
