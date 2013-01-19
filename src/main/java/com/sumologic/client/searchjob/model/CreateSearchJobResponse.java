package com.sumologic.client.searchjob.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class CreateSearchJobResponse {

    // Instance fields.

    private String id;

    // Implementation.

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
