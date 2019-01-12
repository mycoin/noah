/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.breakidea.noah.configure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

import com.breakidea.noah.web.velocity.VelocityViewResolver;

/**
 * {@link ConfigurationProperties} for configuring Velocity.
 *
 * @author Andy Wilkinson
 * @since 1.1.0
 */
@ConfigurationProperties(prefix = "spring.velocity")
public class VelocityProperties extends AbstractTemplateViewResolverProperties {

	public static final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";

	public static final String DEFAULT_PREFIX = "";

	public static final String DEFAULT_SUFFIX = ".vm";

	/**
	 * Additional velocity properties.
	 */
	private Map<String, String> properties = new HashMap<String, String>();

	/**
	 * Template path.
	 */
	private String resourceLoaderPath = DEFAULT_RESOURCE_LOADER_PATH;

	/**
	 * Prefer file system access for template loading. File system access enables hot detection of template changes.
	 */
	private boolean preferFileSystemAccess = true;

	/**
	 * path for velocity.properties
	 */
	private Resource configLocation;

	public VelocityProperties() {
		super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
	}

	public Map<String, String> getProperties() {
		return this.properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getResourceLoaderPath() {
		return this.resourceLoaderPath;
	}

	public void setResourceLoaderPath(String resourceLoaderPath) {
		this.resourceLoaderPath = resourceLoaderPath;
	}

	public boolean isPreferFileSystemAccess() {
		return this.preferFileSystemAccess;
	}

	public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
		this.preferFileSystemAccess = preferFileSystemAccess;
	}

	public Resource getConfigLocation() {
		return this.configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public void applyToViewResolver(Object viewResolver) {
		Assert.isInstanceOf(AbstractTemplateViewResolver.class, viewResolver,
				"ViewResolver is not an instance of AbstractTemplateViewResolver :" + viewResolver);
		VelocityViewResolver resolver = (VelocityViewResolver) viewResolver;
		resolver.setPrefix(getPrefix());
		resolver.setSuffix(getSuffix());
		resolver.setCache(isCache());

		if (getContentType() != null) {
			resolver.setContentType(getContentType().toString());
		}

		resolver.setViewNames(getViewNames());
		resolver.setExposeRequestAttributes(isExposeRequestAttributes());
		resolver.setAllowRequestOverride(isAllowRequestOverride());
		resolver.setAllowSessionOverride(isAllowSessionOverride());
		resolver.setExposeSessionAttributes(isExposeSessionAttributes());
		resolver.setExposeSpringMacroHelpers(isExposeSpringMacroHelpers());
		resolver.setRequestContextAttribute(getRequestContextAttribute());

		// The resolver usually acts as a fallback resolver (e.g. like a
		// InternalResourceViewResolver) so it needs to have low precedence
		resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
		//
		// resolver.setDateToolAttribute(getDateToolAttribute());
		// resolver.setNumberToolAttribute(getNumberToolAttribute());
	}
}
