/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
        if (value == null) {
            return null;
        }
        return ((Number) value).longValue();
    }

    public static Double asDouble(Object value) {
        if (value == null) {
            return null;
        }
        return ((Number) value).doubleValue();
    }

    public static boolean isValidJson(String json) {
        try {
            MAPPER.readValue(json, JsonNode.class);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
