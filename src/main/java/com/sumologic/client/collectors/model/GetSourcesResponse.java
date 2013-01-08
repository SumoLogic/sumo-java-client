package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A response containing a list of sources.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetSourcesResponse {

    private List<Source> sources;

    /**
     * Returns the sources.
     *
     * @return The sources.
     */
    public List<Source> getSources() {
        return sources;
    }
}
