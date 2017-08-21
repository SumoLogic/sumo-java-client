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

/**
 * API errors that might occur during a search.
 */
public enum SumoSearchErrors implements SumoServerError {
    EMPTY_FIELD_LIST("no.fields"),
    INVALID_TIMESTAMP_TO("invalid.timestamp.to"),
    INVALID_TIMESTAMP_FROM("invalid.timestamp.from"),
    TO_SMALLER_THAN_FROM("to.smaller.than.from"),
    UNKNOWN_TIMEZONE("unknown.timezone"),
    EMPTY_TIMEZONE("empty.timezone"),
    NO_QUERY_STRING("no.q"),
    TIMED_OUT("timed.out"),
    CANCELLED("cancelled"),
    RETURNED_ERRORS("errors"),
    NO_COUNTS("no.counts"),
    QUERY_PARSE_ERROR("parse.error"),
    MESSAGE_RETRIEVAL_ERROR("message.retrieval.error"),
    FORMAT_DOES_NOT_ALLOW_AGGREGATION("aggregates.unsupported"),
    FORMAT_DOES_NOT_ALLOW_RAW("raw.unsupported"),
    OUTPUT_CLOSED("output.closed"),
    FORMAT_UNKNOWN("unknown.format");

    private String id;

    private SumoSearchErrors(String id) {
        this.id = "search." + id;
    }

    public String getId() {
        return id;
    }
}
