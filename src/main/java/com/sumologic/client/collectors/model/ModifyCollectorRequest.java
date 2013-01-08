package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpPutRequest;

/**
 * A request to modify a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class ModifyCollectorRequest implements HttpPutRequest {

    private Long id;
    private Collector collector;

    public ModifyCollectorRequest(Long id, Collector collector) {
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
