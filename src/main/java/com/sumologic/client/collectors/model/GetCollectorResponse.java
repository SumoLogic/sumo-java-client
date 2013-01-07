package com.sumologic.client.collectors.model;

/**
 * A response containing a collector.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetCollectorResponse {

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
