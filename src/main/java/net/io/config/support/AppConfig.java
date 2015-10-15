package net.io.config.support;

import java.io.IOException;
import java.nio.charset.Charset;

import net.io.config.util.ContextUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@Configuration
public class AppConfig {

	public static final String CHARSET_NAME = "UTF-8";

	public static final Charset CHARSET = Charset.forName(CHARSET_NAME);

	public static final String DEFAULT_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	@Bean
	public VelocityConfigurer volocityConfig() throws IOException {
		VelocityConfigurer config = new VelocityConfigurer();
		Resource configFile = ContextUtils.getResource("/WEB-INF/conf/velocity.properties");

		config.setConfigLocation(configFile);

		String[] templateDirs = new String[] { "/META-INF/views", "/META-INF/external", "/META-INF/views/common" };
		config.setResourceLoaderPath(StringUtils.arrayToCommaDelimitedString(templateDirs));

		return config;
	}


}
