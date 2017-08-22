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
package com.sumologic.client.collectors;

import com.sumologic.client.SumoServerError;

/**
 * Errors that might occur during collector API requests.
 */
public enum SumoCollectorErrors implements SumoServerError {
    INVALID_COLLECTOR("collector.invalid"),
    INVALID_SOURCE("source.invalid"),
    CANNOT_MODIFY_COLLECTOR("collector.forbidden"),
    BAD_COLLECTOR_ID("collector.request.id.invalid"),
    BAD_SOURCE_ID("source.request.id.invalid"),
    INVALID_COLLECTOR_TYPE("collector.type.invalid"),
    DUPLICATE_RESOURCE_NAME("validation.name.duplicate"),
    MISSING_FIELDS("validation.fields.missing"),
    INVALID_FIELD_VALUE("validation.fields.invalid"),
    INVALID_SOURCE_TYPE("source.type.invalid");

    private String id;

    private SumoCollectorErrors(String id) {
        this.id = "collectors." + id;
    }

    public String getId() {
        return id;
    }
}
