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
package com.sumologic.client.searchjob;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.searchjob.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import org.apache.http.HttpStatus;

/**
 * Client for the search jobs API.
 */
public class SearchJobClient {

    private HttpUtils httpUtils;

    public SearchJobClient(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public String createSearchJob(
            ConnectionConfig connection,
            CreateSearchJobRequest createSearchJobRequest) {

        String uri = UrlParameters.SEARCH_JOBS_SERVICE;
        return httpUtils.post(
                connection,
                uri,
                createSearchJobRequest,
                HttpUtils.toRequestHeaders(
                        "Content-type", "application/json",
                        "Accept", "application/json"),
                new DeserializingResponseHandler<CreateSearchJobRequest,
                        CreateSearchJobResponse>(CreateSearchJobResponse.class),
                HttpStatus.SC_ACCEPTED).getId();
    }

    public GetSearchJobStatusResponse getSearchJobStatus(
            ConnectionConfig connection,
            GetSearchJobStatusRequest getSearchJobStatusRequest) {

        String uri = UrlParameters.SEARCH_JOBS_SERVICE +
                "/" + getSearchJobStatusRequest.getId();
        return httpUtils.get(
                connection,
                uri,
                getSearchJobStatusRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetSearchJobStatusRequest,
                        GetSearchJobStatusResponse>(GetSearchJobStatusResponse.class),
                HttpStatus.SC_OK);
    }

    public GetMessagesForSearchJobResponse getMessagesForSearchJob(
            ConnectionConfig connection,
            GetMessagesForSearchJobRequest getMessagesForSearchJobRequest) {

        String uri = UrlParameters.SEARCH_JOBS_SERVICE +
                "/" + getMessagesForSearchJobRequest.getId() +
                "/" + UrlParameters.SEARCH_JOBS_SERVICE_MESSAGES;
        return httpUtils.get(
                connection,
                uri,
                getMessagesForSearchJobRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetMessagesForSearchJobRequest,
                        GetMessagesForSearchJobResponse>(GetMessagesForSearchJobResponse.class),
                HttpStatus.SC_OK);
    }

    public GetRecordsForSearchJobResponse getRecordsForSearchJob(
            ConnectionConfig connection,
            GetRecordsForSearchJobRequest getRecordsForSearchJobRequest) {

        String uri = UrlParameters.SEARCH_JOBS_SERVICE +
                "/" + getRecordsForSearchJobRequest.getId() +
                "/" + UrlParameters.SEARCH_JOBS_SERVICE_RECORDS;
        return httpUtils.get(
                connection,
                uri,
                getRecordsForSearchJobRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetRecordsForSearchJobRequest,
                        GetRecordsForSearchJobResponse>(GetRecordsForSearchJobResponse.class),
                HttpStatus.SC_OK);
    }

    public CancelSearchJobResponse deleteSearchJob(
            ConnectionConfig connection,
            CancelSearchJobRequest cancelSearchJobRequest) {

        String uri = UrlParameters.SEARCH_JOBS_SERVICE +
                "/" + cancelSearchJobRequest.getId();
        return httpUtils.delete(
                connection,
                uri,
                cancelSearchJobRequest,
                new DeserializingResponseHandler<CancelSearchJobRequest,
                        CancelSearchJobResponse>(CancelSearchJobResponse.class),
                HttpStatus.SC_OK);
    }
}
