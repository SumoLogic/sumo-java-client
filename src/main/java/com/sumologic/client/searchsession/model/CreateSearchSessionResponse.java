package com.sumologic.client.searchsession.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class CreateSearchSessionResponse {

    // Instance fields.

    private String id;

    // Implementation.

    /**
     * Returns the ID of the session.
     *
     * @return The ID of the session.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the session.
     *
     * @param id The ID of the session.
     */
    public void setId(String id) {
        this.id = id;
    }
}
