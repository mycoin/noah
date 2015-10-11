package org.ionnic.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@Configuration
public class AppConfig {

	public static final String VELOCITY_CONF = "/WEB-INF/conf/velocity.properties";

	public static final String[] TEMPLATE_PATHS = { "/META-INF/views", "/META-INF/external", "/META-INF/views/common" };

	@Bean
	public VelocityConfigurer volocityConfig() throws IOException {
		VelocityConfigurer config = new VelocityConfigurer();
		Resource configFile = ContextSupport.getResource(VELOCITY_CONF);

		config.setConfigLocation(configFile);
		config.setResourceLoaderPath(StringUtils.arrayToCommaDelimitedString(TEMPLATE_PATHS));

		return config;
	}

}
