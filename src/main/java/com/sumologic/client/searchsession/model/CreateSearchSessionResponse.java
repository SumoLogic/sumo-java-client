package com.sumologic.client.searchsession.model;

import com.sumologic.client.model.HttpPostRequest;
import com.sumologic.client.util.JacksonUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

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
