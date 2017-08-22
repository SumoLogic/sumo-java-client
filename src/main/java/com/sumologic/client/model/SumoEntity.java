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
package com.sumologic.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SumoEntity {

    private String eTag;

    /**
     * Gets the ETag.
     *
     * @return The ETag.
     */
    @JsonIgnore
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the ETag. Used for optimistic locking; if an invalid ETag is provided, update
     * operations will fail, so only modify this if you know what you're doing.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
