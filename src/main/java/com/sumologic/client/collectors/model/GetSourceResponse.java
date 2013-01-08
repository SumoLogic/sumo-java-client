package com.sumologic.client.collectors.model;

/**
 * A response containing the requested source.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetSourceResponse {

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
