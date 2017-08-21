package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpPostRequest;

/**
 * A request to create a collector in the Sumo Logic system.
 */
public class CreateCollectorRequest implements HttpPostRequest {

    private Collector collector;

    public CreateCollectorRequest(Collector collector) {
        this.collector = collector;
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
