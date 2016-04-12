package net.breakidea.app.common.view;

import net.breakidea.common.util.ContextUtils;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author apple
 *
 */
public class LangTool {

    private WebApplicationContext applicationContext = ContextUtils.getApplicationContext();

    /**
     * @param key
     * @return
     */
    public String get( String key ) {
        return get(key, "");
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String get( String key, String defaultValue ) {
        return applicationContext.getMessage(key, null, defaultValue, null);
    }
}