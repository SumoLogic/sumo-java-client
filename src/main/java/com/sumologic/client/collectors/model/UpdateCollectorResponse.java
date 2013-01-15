package com.sumologic.client.collectors.model;

import com.sumologic.client.model.SumoEntity;
import com.sumologic.client.model.SumoEntityResponse;

/**
 * A response containing the updated collector.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class UpdateCollectorResponse extends SumoEntityResponse {

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
