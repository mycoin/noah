package org.ionnic.common.util;

import java.lang.reflect.Type;
import java.util.Map;

import org.ionnic.common.support.AppConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author apple
 *
 */
public abstract class JsonUtils {

    private static final String DEFAULT_JSON = "{}";

    private static Gson gson;

    /**
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json) {
        return fromJson(json, Map.class);
    }

    /**
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }

        return getGson().fromJson(json, clazz);
    }

    /**
     * @param json
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type) {
        if (json == null) {
            return null;
        }
        try {
            return (T) getGson().fromJson(json, type);
        } catch (JsonSyntaxException e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * @return
     */
    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.serializeNulls();
            gb.disableHtmlEscaping();
            gb.setDateFormat(AppConfig.DATE_FORMAT);

            gson = gb.create();
        }
        return gson;
    }

    /**
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        try {
            return getGson().toJson(src);
        } catch (Exception e) {

        }
        return DEFAULT_JSON;
    }
}
