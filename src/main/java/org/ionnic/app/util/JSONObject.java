package org.ionnic.app.util;

import org.ionnic.config.util.JsonUtils;

public class JSONObject {

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
