package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A response containing a list of collectors.
 *
 * @author Jeffrey Wang
 */
public class GetCollectorsResponse {

    private List<Collector> collectors;

    /**
     * Returns the collectors.
     *
     * @return The collectors.
     */
    public List<Collector> getCollectors() {
        return collectors;
    }
}
