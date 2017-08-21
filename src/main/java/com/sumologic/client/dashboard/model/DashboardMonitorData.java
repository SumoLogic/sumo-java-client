package com.sumologic.client.dashboard.model;

import java.util.List;

import com.sumologic.client.searchjob.model.SearchJobField;
import com.sumologic.client.searchjob.model.SearchJobRecord;

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
