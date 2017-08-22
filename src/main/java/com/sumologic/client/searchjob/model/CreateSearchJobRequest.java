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

import com.sumologic.client.model.HttpPostRequest;

public final class CreateSearchJobRequest implements HttpPostRequest {

    private String query;
    private String from;
    private String to;
    private String timeZone;

    /**
     * Creates a search job request.
     *
     * @param query    The query.
     * @param from     The start of the time range.
     * @param to       The end of the time range.
     * @param timeZone The time zone.
     */
    public CreateSearchJobRequest(String query,
                                  String from,
                                  String to,
                                  String timeZone) {
        this.query = query;
        this.from = from;
        this.to = to;
        this.timeZone = timeZone;
    }

    /**
     * Returns the query.
     *
     * @return The query.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query.
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Sets the query.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withQuery(String query) {
        setQuery(query);
        return this;
    }

    /**
     * Returns the start of the time range.
     *
     * @return The start of the time range.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start of the time range.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Sets the start of the time range.
     *
     * @param from The start of the time range.
     * @return This object.
     */
    public CreateSearchJobRequest withFrom(String from) {
        setFrom(from);
        return this;
    }

    /**
     * Returns the end of the time range.
     *
     * @return The end of the time range.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end of the time range.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Sets the end of the time range.
     *
     * @param to The end of the time range.
     * @return This object.
     */
    public CreateSearchJobRequest withTo(String to) {
        setTo(to);
        return this;
    }

    /**
     * Returns the time zone.
     *
     * @return The time zone.
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the time zone.
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Sets the time zone.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withTimeZone(String timeZone) {
        setTimeZone(timeZone);
        return this;
    }
}
