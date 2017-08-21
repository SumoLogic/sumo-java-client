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
package com.sumologic.client.dashboard.model;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import com.sumologic.client.model.HttpGetRequest;

public final class GetDashboardDataRequest implements HttpGetRequest {

    private long id;

    /**
     * Creates a get dashboards request.
     *
     * @param id The ID of the dashboard.
     */
    public GetDashboardDataRequest(long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the dashboard.
     *
     * @return The ID of the dashboard.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the dashboard.
     *
     * @param id The ID of the dashboard.
     */
    public void setId(long id) {
        this.id = id;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
