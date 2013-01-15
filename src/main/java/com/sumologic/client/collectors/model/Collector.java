package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sumologic.client.model.SumoEntity;

/**
 * A collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "collectorType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InstallableCollector.class, name = "Installable"),
        @JsonSubTypes.Type(value = HostedCollector.class, name = "Hosted")
})
public abstract class Collector extends SumoEntity {

    private Long id;
    private String name;
    private Boolean alive;
    private String hostName;
    private String timeZone;
    private String category;
    private String status;
    private String description;
    private String collectorVersion;
    private Boolean ephemeral;

    /**
     * Returns the id.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns whether the collector is alive.
     *
     * @return Whether the collector is alive.
     */
    public Boolean isAlive() {
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
     * Sets the host name.
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
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
     * Sets the time zone.
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
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
     * Sets the category.
     */
    public void setCategory(String category) {
        this.category = category;
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
     * Sets the description.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Returns whether the collector is ephemeral.
     *
     * @return Whether the collector is ephemeral.
     */
    public Boolean isEphemeral() {
        return ephemeral;
    }

    /**
     * Sets whether the collector is ephemeral.
     */
    public void setEphemeral(Boolean ephemeral) {
        this.ephemeral = ephemeral;
    }
}
