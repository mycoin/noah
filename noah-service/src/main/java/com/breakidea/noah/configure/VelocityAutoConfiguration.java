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

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import com.breakidea.noah.web.velocity.VelocityConfig;
import com.breakidea.noah.web.velocity.VelocityConfigurer;
import com.breakidea.noah.web.velocity.VelocityEngineFactory;
import com.breakidea.noah.web.velocity.VelocityEngineFactoryBean;
import com.breakidea.noah.web.velocity.VelocityLayoutViewResolver;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Velocity.
 *
 * @author Andy Wilkinson
 * @author Brian Clozel
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass({ VelocityEngine.class, VelocityEngineFactory.class })
@EnableConfigurationProperties(VelocityProperties.class)
public class VelocityAutoConfiguration {

	private static final Log logger = LogFactory.getLog(VelocityAutoConfiguration.class);

	private final ApplicationContext applicationContext;

	private final VelocityProperties properties;

	public VelocityAutoConfiguration(ApplicationContext applicationContext, VelocityProperties properties) {
		this.applicationContext = applicationContext;
		this.properties = properties;
	}

	@PostConstruct
	public void checkTemplateLocationExists() {
		if (this.properties.isCheckTemplateLocation()) {
			TemplateLocation location = new TemplateLocation(this.properties.getResourceLoaderPath());
			if (!location.exists(this.applicationContext)) {
				logger.warn("Cannot find template location: " + location
						+ " (please add some templates, check your Velocity " + "configuration, or set spring.velocity."
						+ "checkTemplateLocation=false)");
			}
		}
	}

	protected static class VelocityConfiguration {

		@Autowired
		protected VelocityProperties properties;

		protected void applyProperties(VelocityEngineFactory factory) {
			Properties velocityProperties = new Properties();

			factory.setResourceLoaderPath(properties.getResourceLoaderPath());
			factory.setPreferFileSystemAccess(properties.isPreferFileSystemAccess());
			factory.setConfigLocation(properties.getConfigLocation());

			velocityProperties.setProperty("input.encoding", properties.getCharsetName());
			velocityProperties.putAll(properties.getProperties());

			factory.setVelocityProperties(velocityProperties);

		}
	}

	@Configuration
	@ConditionalOnNotWebApplication
	public static class VelocityNonWebConfiguration extends VelocityConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public VelocityEngineFactoryBean velocityConfiguration() {
			VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
			applyProperties(velocityEngineFactoryBean);
			return velocityEngineFactoryBean;
		}

	}

	@Configuration
	@ConditionalOnClass(Servlet.class)
	@ConditionalOnWebApplication
	public static class VelocityWebConfiguration extends VelocityConfiguration {

		@Bean
		@ConditionalOnMissingBean(VelocityConfig.class)
		public VelocityConfigurer velocityConfigurer() {
			VelocityConfigurer configurer = new VelocityConfigurer();
			applyProperties(configurer);
			return configurer;
		}

		@Bean
		public VelocityEngine velocityEngine(VelocityConfigurer configurer) throws VelocityException, IOException {
			return configurer.getVelocityEngine();
		}

		@Bean
		@ConditionalOnMissingBean(name = "velocityViewResolver")
		@ConditionalOnProperty(name = "spring.velocity.enabled", matchIfMissing = true)
		public VelocityLayoutViewResolver velocityViewResolver() {
			VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();
			this.properties.applyToViewResolver(resolver);
			return resolver;
		}

		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnEnabledResourceChain
		public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
			return new ResourceUrlEncodingFilter();
		}

	}

}
