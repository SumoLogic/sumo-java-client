package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get sources for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetSourcesRequest implements HttpGetRequest {

    private Long collectorId;
    private Long limit;
    private Long offset;

    public GetSourcesRequest(Long collectorId) {
        this.collectorId = collectorId;
    }

    /**
     * Returns the collector id.
     *
     * @return The collector id.
     */
    public Long getCollectorId() {
        return collectorId;
    }

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
    public GetSourcesRequest withLimit(Long limit) {
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
    public GetSourcesRequest withOffset(Long offset) {
        setOffset(offset);
        return this;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
