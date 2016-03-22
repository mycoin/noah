package org.ionnic.common.support;

import org.ionnic.common.util.GsonUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;

/**
 * @author apple
 *
 */
public class JsonHttpMessageConverter extends GsonHttpMessageConverter {

    private Gson gson = GsonUtils.getGson();

    /**
     * set Gson
     */
    public JsonHttpMessageConverter() {
        setGson(gson);
    }

    @Override
    public Gson getGson() {
        return gson;
    }
}
