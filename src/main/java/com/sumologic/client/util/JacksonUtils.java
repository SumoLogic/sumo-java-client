package com.sumologic.client.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    // This is a safe and recommended way to use the ObjectMapper.
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // Don't want old client libraries to break if we add fields.
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Only serialize non-null values.
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static Long asLong(Object value) {
        return ((Number) value).longValue();
    }

    public static Double asDouble(Object value) {
        return ((Number) value).doubleValue();
    }
}
