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
package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpDeleteRequest;

/**
 * A request to delete a source for a collector in the Sumo Logic system.
 */
public class DeleteSourceRequest implements HttpDeleteRequest {

    private Long collectorId;
    private Long sourceId;

    public DeleteSourceRequest(Long collectorId, Long sourceId) {
        this.collectorId = collectorId;
        this.sourceId = sourceId;
    }

    /**
     * Returns the collector id.
     *
     * @return The collector id.
     */
    public Long getCollectorId() {
        return collectorId;
    }

    /**
     * Returns the source id.
     *
     * @return The source id.
     */
    public Long getSourceId() {
        return sourceId;
    }
}
