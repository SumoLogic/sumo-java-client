package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpDeleteRequest;

/**
 * A request to delete a source for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class DeleteSourceRequest implements HttpDeleteRequest {

    private Long collectorId;
    private Long sourceId;

    public DeleteSourceRequest(Long collectorId, Long sourceId) {
        this.collectorId = collectorId;
        this.sourceId = sourceId;
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
     * Returns the source id.
     *
     * @return The source id.
     */
    public Long getSourceId() {
        return sourceId;
    }
}
