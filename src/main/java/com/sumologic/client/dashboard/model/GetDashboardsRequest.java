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
package com.sumologic.client.dashboard.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

public final class GetDashboardsRequest implements HttpGetRequest {

    private boolean includeMonitors;

    /**
     * Creates a get dashboards request.
     *
     * @param includeMonitors Whether to include the monitors.
     */
    public GetDashboardsRequest(boolean includeMonitors) {
        this.includeMonitors = includeMonitors;
    }

    /**
     * Gets whether to include the monitors in the response.
     *
     * @return Whether to include the monitors in the response.
     */
    public boolean isIncludeMonitors() {
        return includeMonitors;
    }

    /**
     * Sets whether to include the monitors in the response.
     *
     * @param includeMonitors Whether to include the monitors in the response.
     */
    public void setIncludeMonitors(boolean includeMonitors) {
        this.includeMonitors = includeMonitors;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
