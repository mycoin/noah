package org.ionnic.common.web;

import org.ionnic.common.util.JsonUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author apple
 *
 */
public class JsonHttpMessageConverter extends GsonHttpMessageConverter {

    /**
     * set Gson
     */
    public JsonHttpMessageConverter() {
        super();
        setGson(JsonUtils.getGson());
    }
}
