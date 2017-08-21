package com.sumologic.client.dashboard.model;

public class GetDashboardResponse {

    private long id;
    private Dashboard dashboard;

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
