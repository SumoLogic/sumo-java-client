package com.sumologic.client.dashboard.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetDashboardResponse {

    // Instance fields.

    private long id;
    private Dashboard dashboard;

    // Implementation.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
