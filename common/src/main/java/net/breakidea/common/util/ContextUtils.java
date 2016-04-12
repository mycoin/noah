package net.breakidea.common.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author apple
 *
 */
public abstract class ContextUtils {

    /**
     * @return
     */
    public static WebApplicationContext getApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }
}
