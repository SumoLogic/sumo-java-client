package com.sumologic.client.searchsession.model;

import com.sumologic.client.model.HttpGetRequest;
import com.sumologic.client.model.HttpPostRequest;
import com.sumologic.client.util.JacksonUtils;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetSearchSessionStatusRequest implements HttpGetRequest {

    // Instance fields.

    private String id;

    // Implementation.

    /**
     * Creates a get search session status request.
     *
     * @param id The id of the search session.
     */
    public GetSearchSessionStatusRequest(String id) {
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

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return new ArrayList<NameValuePair>(0);
    }
}
