package com.sumologic.client.dashboard.model;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetDashboardDataResponse {

    // Instance fields.

    private long id;
    private List<DashboardMonitorData> dashboardMonitorDatas;

    // Implementation.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DashboardMonitorData> getDashboardMonitorDatas() {
        return dashboardMonitorDatas;
    }

    public void setDashboardMonitorDatas(List<DashboardMonitorData> dashboardMonitorDatas) {
        this.dashboardMonitorDatas = dashboardMonitorDatas;
    }
}
