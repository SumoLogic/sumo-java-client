package com.sumologic.client.collectors.model;

/**
 * An installable collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class InstallableCollector extends Collector {

    private String osVersion;
    private String osName;
    private String osArch;
    private Long upTime;

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
    public Long getUpTime() {
        return upTime;
    }
}
