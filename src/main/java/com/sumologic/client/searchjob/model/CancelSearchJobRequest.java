package com.sumologic.client.searchjob.model;

import com.sumologic.client.model.HttpDeleteRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class CancelSearchJobRequest implements HttpDeleteRequest {

    // Instance fields.

    private String id;

    // Implementation.

    /**
     * Creates a get search job status request.
     *
     * @param id The id of the search job.
     */
    public CancelSearchJobRequest(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the search job.
     *
     * @return The ID of the search job.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the search job.
     *
     * @param id The ID of the search job.
     */
    public void setId(String id) {
        this.id = id;
    }
}
