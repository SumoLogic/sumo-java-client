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
package com.sumologic.client.searchjob.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

public final class GetSearchJobStatusRequest implements HttpGetRequest {

    private String id;

    /**
     * Creates a get search job status request.
     *
     * @param id The id of the search job.
     */
    public GetSearchJobStatusRequest(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the search job.
     *
     * @return The ID of the search job.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the search job.
     *
     * @param id The ID of the search job.
     */
    public void setId(String id) {
        this.id = id;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
