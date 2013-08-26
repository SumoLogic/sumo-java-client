package com.sumologic.client.dashboard.model;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class Dashboard {

    // Instance fields.

    private long id;
    private String title;
    private String description;
    private List<DashboardMonitor> dashboardMonitors;
    private String properties;

    // Implementation.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DashboardMonitor> getDashboardMonitors() {
        return dashboardMonitors;
    }

    public void setDashboardMonitors(List<DashboardMonitor> dashboardMonitors) {
        this.dashboardMonitors = dashboardMonitors;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
