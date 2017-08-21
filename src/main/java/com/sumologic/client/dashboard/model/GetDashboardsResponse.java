package com.sumologic.client.dashboard.model;

import java.util.List;

public class GetDashboardsResponse {

    private List<Dashboard> dashboards;

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(List<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }
}
