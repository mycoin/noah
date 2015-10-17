package org.ionnic.config.support;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Configuration;

/**
 * @author apple
 *
 */
@Configuration
public class AppConfig {

	public static final String CHARSET_NAME = "UTF-8";

	public static final Charset CHARSET = Charset.forName(CHARSET_NAME);

	public static final String DEFAULT_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

}
