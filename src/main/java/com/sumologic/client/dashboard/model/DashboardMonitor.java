package com.sumologic.client.dashboard.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class DashboardMonitor {

    // Instance fields.

    private long id;
    private long dashboardId;
    private String queryId;
    private String title;
    private String viewerType;
    private int detailLevel;
    private String queryString;
    private String timeRange;
    private long savedSearchId;
    private int x;
    private int y;
    private int width;
    private int height;
    private String properties;
    private boolean isDisabled;

    // Implementation.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(long dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewerType() {
        return viewerType;
    }

    public void setViewerType(String viewerType) {
        this.viewerType = viewerType;
    }

    public int getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(int detailLevel) {
        this.detailLevel = detailLevel;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public long getSavedSearchId() {
        return savedSearchId;
    }

    public void setSavedSearchId(long savedSearchId) {
        this.savedSearchId = savedSearchId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
