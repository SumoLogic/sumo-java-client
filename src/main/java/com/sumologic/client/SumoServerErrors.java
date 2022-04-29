/*
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
 * General sumo API web service errors.
 */
public enum SumoServerErrors implements SumoServerError {
    INTERNAL_SERVER_ERROR("internal.error"),
    UNAUTHORIZED("unauthorized"),
    UNAVAILABLE("service.unavailable"),
    NOT_FOUND("notfound"),
    UNSUPPORTED_METHOD("method.unsupported"),
    INVALID_CONTENT_TYPE("contenttype.invalid"),

    ETAG_MISMATCH("service.etagid.mismatch"),
    MISSING_PRECONDITION("service.ifthen.missing"),
    BAD_PRECONDITION("service.ifthen.notquoted"),

    INVALID_LIMIT("service.limit.invalid"),
    INVALID_OFFSET("service.offset.invalid"),
    INVALID_JSON("service.json.invalid");

    private String id = "";

    SumoServerErrors(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
