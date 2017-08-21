package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A source that reads from local Windows event logs.
 */
public class WindowsEventLogSource extends Source {

    private static String LOG_NAMES = "logNames";

    public WindowsEventLogSource() {
        setSourceType(SourceType.LOCAL_WINDOWS_EVENT_LOG.getType());
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
