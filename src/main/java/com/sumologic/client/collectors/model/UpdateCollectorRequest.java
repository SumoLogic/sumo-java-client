package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumologic.client.model.HttpPutRequest;

/**
 * A request to modify a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class UpdateCollectorRequest implements HttpPutRequest {

    @JsonIgnore private Long id;
    private Collector collector;

    public UpdateCollectorRequest(Long id, Collector collector) {
        this.id = id;
        this.collector = collector;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the collector.
     *
     * @return The collector.
     */
    public Collector getCollector() {
        return collector;
    }
}
