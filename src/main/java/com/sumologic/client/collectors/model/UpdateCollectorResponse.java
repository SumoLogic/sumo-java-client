package com.sumologic.client.collectors.model;

/**
 * A response containing the modified collector.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class UpdateCollectorResponse {

    private Collector collector;

    /**
     * Returns the collector.
     *
     * @return The collector.
     */
    public Collector getCollector() {
        return collector;
    }
}
