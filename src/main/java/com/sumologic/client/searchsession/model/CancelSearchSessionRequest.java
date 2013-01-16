package com.sumologic.client.searchsession.model;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import com.sumologic.client.model.HttpDeleteRequest;
import com.sumologic.client.model.HttpGetRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class CancelSearchSessionRequest implements HttpDeleteRequest {

    // Instance fields.

    private String id;

    // Implementation.

    /**
     * Creates a get search session status request.
     *
     * @param id The id of the search session.
     */
    public CancelSearchSessionRequest(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the search session.
     *
     * @return The ID of the search session.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the search session.
     *
     * @param id The ID of the search session.
     */
    public void setId(String id) {
        this.id = id;
    }
}
