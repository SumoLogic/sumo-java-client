package com.sumologic.client.dashboard.model;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetDashboardsResponse {

    // Instance fields.

    private List<Dashboard> dashboards;

    // Implementation.

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(List<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }
}
