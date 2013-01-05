package com.sumologic.client.model;

import java.util.List;

public class GetCollectorsResponse {

    private List<Collector> collectors;

    public GetCollectorsResponse() { /* This class is automatically deserialized from JSON. */ }

    /**
     * Returns the collectors.
     *
     * @return The collectors.
     */
    public List<Collector> getCollectors() {
        return collectors;
    }
}
