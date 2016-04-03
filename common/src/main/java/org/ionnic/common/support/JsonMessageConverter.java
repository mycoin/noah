package org.ionnic.common.support;

import org.ionnic.common.util.JsonUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author apple
 *
 */
public class JsonMessageConverter extends GsonHttpMessageConverter {

    /**
     * set Gson
     */
    public JsonMessageConverter() {
        super();
        setGson(JsonUtils.getGson());
    }
}
