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

import com.sumologic.client.searchjob.model.SearchJobField;
import com.sumologic.client.searchjob.model.SearchJobRecord;

import java.util.List;

public class DashboardMonitorData {

    private long id;
    private List<SearchJobField> fields;
    private List<SearchJobRecord> records;
    private ResolvedTimeRange resolvedTimeRange;
    private boolean isRecentlyStarted;
    private long queryStartTime;
    private boolean lastUpdated;
    private List<String> warnings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SearchJobField> getFields() {
        return fields;
    }

    public void setFields(List<SearchJobField> fields) {
        this.fields = fields;
    }

    public List<SearchJobRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SearchJobRecord> records) {
        this.records = records;
    }

    public ResolvedTimeRange getResolvedTimeRange() {
        return resolvedTimeRange;
    }

    public void setResolvedTimeRange(ResolvedTimeRange resolvedTimeRange) {
        this.resolvedTimeRange = resolvedTimeRange;
    }

    public boolean isRecentlyStarted() {
        return isRecentlyStarted;
    }

    public void setRecentlyStarted(boolean recentlyStarted) {
        isRecentlyStarted = recentlyStarted;
    }

    public long getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(long queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public boolean isLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(boolean lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
