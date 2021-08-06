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

import com.sumologic.client.UrlParameters;
import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * A request to get collectors from the Sumo Logic system.
 */
public class GetCollectorsRequest implements HttpGetRequest {

    private Long limit;
    private Long offset;

    /**
     * Returns the limit.
     *
     * @return The limit.
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * Sets the limit.
     *
     * @return This object.
     */
    public GetCollectorsRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /**
     * Returns the offset.
     *
     * @return The offset.
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Sets the offset.
     *
     * @return This object.
     */
    public GetCollectorsRequest withOffset(Long offset) {
        setOffset(offset);
        return this;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (limit != null) {
            params.add(new BasicNameValuePair(UrlParameters.LIMIT, String.valueOf(limit)));
        }
        if (offset != null) {
            params.add(new BasicNameValuePair(UrlParameters.OFFSET, String.valueOf(offset)));
        }
        return params;
    }
}
