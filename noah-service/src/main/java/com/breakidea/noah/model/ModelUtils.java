package com.breakidea.noah.model;

import java.util.Map;

@SuppressWarnings("unchecked")
public class ModelUtils {

    protected static <T> T get(Map<String, Object> map, String key) {
        if (map != null) {
            Object value = map.get(key);

            if (value != null) {
                return (T) value;
            }
        }
        return null;
    }

    protected static <T> T set(Map<String, Object> map, String key, T value) {
        if (map != null) {

            Object old = map.put(key, value);
            return old != null ? (T) old : null;
        }
        return null;
    }

    protected static <T> T remove(Map<String, Object> map, String key) {
        if (map != null) {
            Object value = map.remove(key);

            return value != null ? (T) value : null;
        }

        return null;
    }

    protected static boolean clear(Map<String, Object> map) {
        if (map != null) {
            map.clear();
            return true;
        }
        return false;
    }
}
