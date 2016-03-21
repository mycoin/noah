package org.ionnic.common.config;

import org.springframework.http.MediaType;

public interface ConfigConstants {

    public static final String CHARSET = "UTF-8";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public static final String DATA = "data";

    public static final String STATUS = "status";

    public static final String STATUS_INFO = "statusInfo";

    public static final MediaType MEDIA_TYPE = new MediaType(CONTENT_TYPE);
}
