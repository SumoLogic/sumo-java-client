package com.sumologic.client.dashboard.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class ResolvedTimeRange {

    // Instance fields.

    private long startMillis;
    private long endMillis;

    // Implementation.

    public long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    public long getEndMillis() {
        return endMillis;
    }

    public void setEndMillis(long endMillis) {
        this.endMillis = endMillis;
    }
}
