package org.ionnic.common.support.view.tool;

import org.ionnic.common.util.ContextUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author apple
 *
 */
public class LangTool {

    public WebApplicationContext applicationContext = ContextUtils.getContext();

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