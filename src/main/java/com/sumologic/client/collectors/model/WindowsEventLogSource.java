package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A source that reads from local Windows event logs.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class WindowsEventLogSource extends Source {

    private static String INCLUDE_ALL = "includeAll";
    private static String LOG_NAMES = "logNames";

    /**
     * Returns whether all event types should be included.
     *
     * @return Whether all event types should be included.
     */
    public Boolean isIncludeAll() {
        return getProperty(INCLUDE_ALL);
    }

    /**
     * Sets whether all event types should be included.
     */
    public void setIncludeAll(Boolean includeAll) {
        setProperty(INCLUDE_ALL, includeAll);
    }

    /**
     * Returns the list of event types (empty if includeAll = true).
     *
     * @return The list of event types.
     */
    public List<String> getLogNames() {
        return getProperty(LOG_NAMES);
    }

    /**
     * Sets the list of event types.
     */
    public void setLogNames(List<String> logNames) {
        setProperty(LOG_NAMES, logNames);
    }
}
