package com.sumologic.client.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    // This is a safe and recommended way to use the ObjectMapper.
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // Don't want old client libraries to break if we add fields.
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
