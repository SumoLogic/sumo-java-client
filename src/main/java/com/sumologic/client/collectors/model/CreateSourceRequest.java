package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumologic.client.model.HttpPostRequest;

/**
 * A request to add a source for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class CreateSourceRequest implements HttpPostRequest {

    @JsonIgnore private Long collectorId;
    private Source source;

    public CreateSourceRequest(Long collectorId, Source source) {
        this.collectorId = collectorId;
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
     * Returns the source.
     *
     * @return The source.
     */
    public Source getSource() {
        return source;
    }
}
