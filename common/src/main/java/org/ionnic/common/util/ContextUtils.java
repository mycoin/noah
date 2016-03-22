package org.ionnic.common.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public abstract class ContextUtils {

    /**
     * @return
     */
    public static WebApplicationContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

}
