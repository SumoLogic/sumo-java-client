package com.sumologic.client.dashboard.model;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import com.sumologic.client.model.HttpGetRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetDashboardRequest implements HttpGetRequest {

    // Instance fields.

    private long id;

    // Implementation.

    /**
     * Creates a get dashboards request.
     *
     * @param id The ID of the dashboard.
     */
    public GetDashboardRequest(long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the dashboard.
     *
     * @return The ID of the dashboard.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the dashboard.
     *
     * @param id The ID of the dashboard.
     */
    public void setId(long id) {
        this.id = id;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
