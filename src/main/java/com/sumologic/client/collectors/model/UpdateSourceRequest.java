package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumologic.client.model.HttpPutRequest;

/**
 * A request to modify a source for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class UpdateSourceRequest implements HttpPutRequest {

    @JsonIgnore private Long collectorId;
    @JsonIgnore private Long sourceId;
    private Source source;

    public UpdateSourceRequest(Long collectorId, Long sourceId, Source source) {
        this.collectorId = collectorId;
        this.sourceId = sourceId;
        this.source = source;
    }

    /**
     * Returns the source id.
     *
     * @return The source id.
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

    /**
     * Returns the source.
     *
     * @return The source.
     */
    public Source getSource() {
        return source;
    }

    @Override
    public String getETag() {
        return source.getETag();
    }
}
