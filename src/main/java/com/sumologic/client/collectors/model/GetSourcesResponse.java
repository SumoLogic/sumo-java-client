package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sumologic.client.util.SourceDeserializer;

import java.util.List;

/**
 * A response containing a list of sources.
 */
public class GetSourcesResponse {

    @JsonDeserialize(contentUsing = SourceDeserializer.class)
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
