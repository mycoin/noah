package org.ionnic.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * @author apple
 *
 */
@Configuration
public class Config {

    public static final String CHARSET = "UTF-8";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public static final MediaType MEDIA_TYPE = new MediaType(CONTENT_TYPE);

}
