package io.redskap.lambda.runtime.util;

import com.google.gson.Gson;

public class JsonMapper {
    private static final Gson GSON = new Gson();

    public static <T> T fromJson(String json, Class<T> type) {
        return GSON.fromJson(json, type);
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }
}
