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
package com.sumologic.client.searchjob.model;

import java.util.Date;

public class SearchJobHistogramBucket {

    private long startTimestamp;
    private long length;
    private int count;

    /**
     * Returns the start timestamp.
     *
     * @return The start timestamp.
     */
    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Sets the start timestamp.
     *
     * @param startTimestamp The start timestamp.
     */
    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * Sets the start timestamp.
     *
     * @param startTimestamp The start timestamp.
     * @return This object.
     */
    public SearchJobHistogramBucket withStartTimestamp(long startTimestamp) {
        setStartTimestamp(startTimestamp);
        return this;
    }

    /**
     * Returns the length.
     *
     * @return The length.
     */
    public long getLength() {
        return length;
    }

    /**
     * Sets the length.
     *
     * @param length
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Sets the length.
     *
     * @param length The length.
     * @return This object.
     */
    public SearchJobHistogramBucket withLength(long length) {
        setLength(length);
        return this;
    }

    /**
     * Returns the count.
     *
     * @return The count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count.
     *
     * @param count The count.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Sets the count.
     *
     * @param count The count.
     * @return This object.
     */
    public SearchJobHistogramBucket withCount(int count) {
        setCount(count);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("startTimestamp: '");
        result.append(new Date(startTimestamp));
        result.append("', length '");
        result.append(length);
        result.append("', count: '");
        result.append(count);
        result.append("'");
        return result.toString();
    }
}