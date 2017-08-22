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
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public final class GetMessagesForSearchJobRequest implements HttpGetRequest {

    private String id;
    private int offset;
    private int limit;

    /**
     * Creates a new messages for search job request.
     *
     * @param id     The search job ID.
     * @param offset The offset.
     * @param limit The limit.
     */
    public GetMessagesForSearchJobRequest(String id, int offset, int limit) {
        this.id = id;
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * Return the search job ID.
     *
     * @return The search job ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the search job ID.
     *
     * @param id The search job ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the offset.
     *
     * @return The offset.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     *
     * @param offset The offset.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Returns the limit.
     *
     * @return The limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit The limit.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        List<NameValuePair> result = new ArrayList<NameValuePair>(2);
        result.add(new BasicNameValuePair("offset", Integer.toString(offset)));
        result.add(new BasicNameValuePair("limit", Integer.toString(limit)));
        return result;
    }
}
