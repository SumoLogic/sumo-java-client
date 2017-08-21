package com.sumologic.client.dashboard.model;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import com.sumologic.client.model.HttpGetRequest;

public final class GetDashboardsRequest implements HttpGetRequest {

    private boolean includeMonitors;

    /**
     * Creates a get dashboards request.
     *
     * @param includeMonitors Whether to include the monitors.
     */
    public GetDashboardsRequest(boolean includeMonitors) {
        this.includeMonitors = includeMonitors;
    }

    /**
     * Gets whether to include the monitors in the response.
     *
     * @return Whether to include the monitors in the response.
     */
    public boolean isIncludeMonitors() {
        return includeMonitors;
    }

    /**
     * Sets whether to include the monitors in the response.
     *
     * @param includeMonitors Whether to include the monitors in the response.
     */
    public void setIncludeMonitors(boolean includeMonitors) {
        this.includeMonitors = includeMonitors;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
