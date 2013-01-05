package com.sumologic.client.model;

/**
 * This class represents a collector in the sumo logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class Collector {

    private String name;
    private Boolean alive;
    private String hostName;
    private String timeZone;
    private String category;
    private String status;
    private String description;
    private String collectorVersion;
    private String osVersion;
    private String osName;
    private String osArch;
    private Long uptime;
    private Boolean ephemeral;

    public Collector() { /* This class is automatically deserialized from JSON. */ }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the collector is alive.
     *
     * @return Whether the collector is alive.
     */
    public Boolean getAlive() {
        return alive;
    }

    /**
     * Returns the host name.
     *
     * @return The host name.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Returns the time zone.
     *
     * @return The time zone.
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Returns the category.
     *
     * @return The category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the status.
     *
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the collector version.
     *
     * @return The collector version.
     */
    public String getCollectorVersion() {
        return collectorVersion;
    }

    /**
     * Returns the OS version.
     *
     * @return The OS version.
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Returns the OS name.
     *
     * @return The OS name.
     */
    public String getOsName() {
        return osName;
    }

    /**
     * Returns the OS architecture.
     *
     * @return The OS architecture.
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     * Returns the collector's uptime.
     *
     * @return The collector's uptime.
     */
    public Long getUptime() {
        return uptime;
    }

    /**
     * Returns whether the collector is ephemeral.
     *
     * @return Whether the collector is ephemeral.
     */
    public Boolean getEphemeral() {
        return ephemeral;
    }
}
