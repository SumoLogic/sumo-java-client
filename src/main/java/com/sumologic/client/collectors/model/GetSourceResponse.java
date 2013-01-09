package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sumologic.client.util.SourceDeserializer;

/**
 * A response containing the requested source.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetSourceResponse {

    @JsonDeserialize(using = SourceDeserializer.class)
    private Source source;

    /**
     * Returns the source.
     *
     * @return The source.
     */
    public Source getSource() {
        return source;
    }
}
