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
package com.sumologic.client.dashboard;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.dashboard.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import org.apache.http.HttpStatus;

public class DashboardClient {

    private final HttpUtils httpUtils;

    public DashboardClient(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public GetDashboardsResponse getDashboards(
            ConnectionConfig connection,
            GetDashboardsRequest getDashboardsRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE;
        return httpUtils.get(
                connection,
                uri,
                getDashboardsRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardsRequest, GetDashboardsResponse>(
                        GetDashboardsResponse.class),
                HttpStatus.SC_OK);
    }

    public GetDashboardResponse getDashboard(
            ConnectionConfig connection,
            GetDashboardRequest getDashboardRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE +
                "/" + getDashboardRequest.getId();
        return httpUtils.get(
                connection,
                uri,
                getDashboardRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardRequest, GetDashboardResponse>(
                        GetDashboardResponse.class),
                HttpStatus.SC_OK);
    }

    public GetDashboardDataResponse getDashboardData(
            ConnectionConfig connection,
            GetDashboardDataRequest getDashboardDataRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE +
                "/" + getDashboardDataRequest.getId() +
                "/" + UrlParameters.DASHBOARDS_SERVICE_DATA;
        return httpUtils.get(
                connection,
                uri,
                getDashboardDataRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardDataRequest, GetDashboardDataResponse>(
                        GetDashboardDataResponse.class),
                HttpStatus.SC_OK);
    }
}
