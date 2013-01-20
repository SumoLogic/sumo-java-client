package com.sumologic.client.searchjob.model;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetSearchJobStatusResponse {

    // Instance fields.

    private String state;
    private List<SearchJobHistogramBucket> histogramBuckets;
    private int messageCount;
    private int recordCount;
    private List<String> pendingWarnings;
    private List<String> pendingErrors;

    // Implementation.

    /**
     * Returns the state.
     *
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state The state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the histogram buckets.
     *
     * @return The histogram buckets.
     */
    public List<SearchJobHistogramBucket> getHistogramBuckets() {
        return histogramBuckets;
    }

    /**
     * Sets the histogram buckets.
     *
     * @param histogramBuckets The histogram buckets.
     */
    public void setHistogramBuckets(List<SearchJobHistogramBucket> histogramBuckets) {
        this.histogramBuckets = histogramBuckets;
    }

    /**
     * Sets the state.
     *
     * @param state The state.
     * @return This object.
     */
    public GetSearchJobStatusResponse withState(String state) {
        setState(state);
        return this;
    }

    /**
     * Returns the number of messages.
     *
     * @return The number of messages.
     */
    public int getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the number of messages.
     *
     * @param messageCount The number of messages.
     */
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    /**
     * Sets the number of messages.
     *
     * @param messageCount The number of messages.
     * @return This object.
     */
    public GetSearchJobStatusResponse withMessageCount(int messageCount) {
        setMessageCount(messageCount);
        return this;
    }

    /**
     * Returns the number of records.
     *
     * @return The number of records.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the number of records.
     *
     * @param recordCount The number of records.
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * Sets the number of records.
     *
     * @param recordCount The number of records.
     * @return This object.
     */
    public GetSearchJobStatusResponse withRecordCount(int recordCount) {
        setRecordCount(recordCount);
        return this;
    }

    /**
     * Returns the pending warnings.
     *
     * @return The pending warnings.
     */
    public List<String> getPendingWarnings() {
        return pendingWarnings;
    }

    /**
     * Sets the pending warnings.
     *
     * @param pendingWarnings The pending warnings.
     */
    public void setPendingWarnings(List<String> pendingWarnings) {
        this.pendingWarnings = pendingWarnings;
    }

    /**
     * Sets the pending warnings.
     *
     * @param pendingWarnings The pending warnings.
     * @return This object.
     */
    public GetSearchJobStatusResponse withPendingWarnings(List<String> pendingWarnings) {
        setPendingWarnings(pendingWarnings);
        return this;
    }

    /**
     * Returns the pending errors.
     *
     * @return The pending errors.
     */
    public List<String> getPendingErrors() {
        return pendingErrors;
    }

    /**
     * Sets the pending errors.
     *
     * @param pendingErrors The pending errors.
     */
    public void setPendingErrors(List<String> pendingErrors) {
        this.pendingErrors = pendingErrors;
    }

    /**
     * Sets the pending errors.
     *
     * @param pendingErrors The pending errors.
     * @return This object.
     */
    public GetSearchJobStatusResponse withPendingErrors(List<String> pendingErrors) {
        setPendingErrors(pendingErrors);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("state: '");
        result.append(state);
        result.append("', histogram buckets: ");
        result.append(histogramBuckets);
        result.append(", message count: '");
        result.append(messageCount);
        result.append("', record count: '");
        result.append(recordCount);
        result.append("', pending warnings: ");
        result.append(pendingWarnings);
        result.append(", pending errors: ");
        result.append(pendingErrors);
        return result.toString();
    }
}
