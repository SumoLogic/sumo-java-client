package com.sumologic.client.searchjob.model;

import java.util.*;

import org.apache.http.NameValuePair;

import com.sumologic.client.model.HttpGetRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetSearchJobStatusRequest implements HttpGetRequest {

    // Instance fields.

    private String id;

    // Implementation.

    /**
     * Creates a get search job status request.
     *
     * @param id The id of the search job.
     */
    public GetSearchJobStatusRequest(String id) {
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

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
