package com.sumologic.client.collectors.model;

import com.sumologic.client.model.SumoEntity;
import com.sumologic.client.model.SumoEntityResponse;

/**
 * A response containing the requested collector.
 *
 * @author Jeffrey Wang
 */
public class GetCollectorResponse extends SumoEntityResponse {

    private Collector collector;

    /**
     * Returns the collector.
     *
     * @return The collector.
     */
    public Collector getCollector() {
        return collector;
    }

    @Override
    protected SumoEntity getEntity() {
        return collector;
    }
}
