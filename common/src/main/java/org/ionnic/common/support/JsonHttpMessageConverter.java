package org.ionnic.common.support;

import org.ionnic.common.support.util.GsonUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author apple
 *
 */
public class JsonHttpMessageConverter extends GsonHttpMessageConverter {

    public JsonHttpMessageConverter() {
        setGson(GsonUtils.getGson());
    }
}
