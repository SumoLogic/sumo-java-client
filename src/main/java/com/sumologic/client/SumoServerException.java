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
package com.sumologic.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sumologic.client.util.JacksonUtils;

import java.util.Map;

/**
 * A sumo server-side exception comprising the HTTP status code,
 * error identifier and code, the query uri used and a human-readable
 * message.
 */
public class SumoServerException extends SumoException {

    private String uri;
    private int status;
    // (message, id, code) == null if the server doesn't respond with a nice JSON
    private String message;
    private String id;
    private String code;

    /**
     * Constructs a server exception when a JSON error message is not available.
     *
     * @param uri The URI that caused the exception
     * @param status The HTTP status
     */
    public SumoServerException(String uri, int status) {
        super("The server responded with HTTP Status: " + status);
        this.uri = uri;
        this.status = status;
        this.message = null;
        this.id = null;
        this.code = null;
    }


    /**
     * Constructs a server exception from a JSON error message
     *
     * @param uri  The URI that caused the exception
     * @param json The JSON error message
     * @throws SumoClientException Thrown if the JSON error message contains syntactic errors.
     */
    public SumoServerException(String uri, String json) throws SumoClientException {
        super(json);
        this.uri = uri;

        // Parse JSON string
        try {
            Map<String, String> kv = JacksonUtils.MAPPER.readValue(json,
                    new TypeReference<Map<String, String>>() {
                    });

            // get fields
            status = Integer.parseInt(kv.get("status"));
            message = kv.get("message");
            id = kv.get("id");
            code = kv.get("code");
        } catch (Exception e) {
            throw new SumoClientException("Exception while parsing JSON: \n" + json, e);
        }
    }

    /**
     * Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    public final int getHTTPStatus() {
        return status;
    }

    /**
     * Returns the identifier of the error., e.g.,
     * "unknown.timezone" when the timezone is unknown
     *
     * @return The identifier of the error
     */
    public final String getErrorId() {
        return id;
    }

    /**
     * Returns the error code.
     *
     * @return The error code.
     */
    public final String getErrorCode() {
        return code;
    }

    /**
     * Returns a the human-readable message associated with
     * this exception.
     *
     * @return A the human-readable message
     */
    public final String getErrorMessage() {
        return message;
    }

    /**
     * Returns the URI that caused the exception.
     *
     * @return The URI that caused the exception.
     */
    public final String getURI() {
        return uri;
    }

    /**
     * Returns true, if the error is equal to a server error, e.g.,
     * @see SumoSearchErrors#EMPTY_FIELD_LIST
     *
     * @param error The error to compare
     * @return True, if the exception equals the given server error
     */
    public boolean equals(SumoServerError error) {
        return id != null &&
                error.getId().equals(id.toLowerCase().trim());
    }
}
