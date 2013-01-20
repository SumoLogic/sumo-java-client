package com.sumologic.client.searchjob.model;

import java.util.Date;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchJobHistogramBucket {

    // Instance fields.

    private long startTimestamp;
    private long length;
    private int count;

    // Implementation.

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