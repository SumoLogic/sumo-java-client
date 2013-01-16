package com.sumologic.client.collectors.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.sumologic.client.model.SumoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A generic source for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 */
public class Source extends SumoEntity {

    private Long id;
    private String name;
    private Boolean automaticDateParsing;
    private Boolean multilineProcessingEnabled;
    private Boolean useAutolineMatching;
    private String manualPrefixRegexp;
    private String category;
    private String hostName;
    private String timeZone;
    private String description;
    private Boolean forceTimeZone;
    private String defaultDateFormat;
    private String sourceType;
    private Boolean alive;
    private String status;
    private List<Filter> filters = new ArrayList<Filter>();
    private Map<String, Object> properties = new HashMap<String, Object>();

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
     * Returns whether automatic date parsing is enabled.
     *
     * @return Whether automatic date parsing is enabled.
     */
    public Boolean isAutomaticDateParsing() {
        return automaticDateParsing;
    }

    /**
     * Sets whether automatic date parsing is enabled.
     */
    public void setAutomaticDateParsing(Boolean automaticDateParsing) {
        this.automaticDateParsing = automaticDateParsing;
    }

    /**
     * Returns whether multiline processing is enabled.
     *
     * @return Whether multiline processing is enabled.
     */
    public Boolean isMultilineProcessingEnabled() {
        return multilineProcessingEnabled;
    }

    /**
     * Sets whether multiline processing is enabled.
     */
    public void setMultilineProcessingEnabled(Boolean multilineProcessingEnabled) {
        this.multilineProcessingEnabled = multilineProcessingEnabled;
    }

    /**
     * Returns whether autoline matching is used.
     *
     * @return Whether autoline matching is used.
     */
    public Boolean isUseAutolineMatching() {
        return useAutolineMatching;
    }

    /**
     * Sets whether autoline matching is used.
     */
    public void setUseAutolineMatching(Boolean useAutolineMatching) {
        this.useAutolineMatching = useAutolineMatching;
    }

    /**
     * Returns the manual prefix regular expression.
     *
     * @return The manual prefix regular expression.
     */
    public String getManualPrefixRegexp() {
        return manualPrefixRegexp;
    }

    /**
     * Sets the manual prefix regular expression.
     */
    public void setManualPrefixRegexp(String manualPrefixRegexp) {
        this.manualPrefixRegexp = manualPrefixRegexp;
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
     * Returns whether the time zone is forced.
     *
     * @return Whether the time zone is forced.
     */
    public Boolean isForceTimeZone() {
        return forceTimeZone;
    }

    /**
     * Sets whether the time zone is forced.
     */
    public void setForceTimeZone(Boolean forceTimeZone) {
        this.forceTimeZone = forceTimeZone;
    }

    /**
     * Returns the default date format.
     *
     * @return The default date format.
     */
    public String getDefaultDateFormat() {
        return defaultDateFormat;
    }

    /**
     * Sets the default date format.
     */
    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }

    /**
     * Returns the source type.
     *
     * @return The source type.
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Sets the source type.
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Returns whether the source is alive.
     *
     * @return Whether the source is alive.
     */
    public Boolean isAlive() {
        return alive;
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
     * Returns the filters.
     *
     * @return The filters.
     */
    public List<Filter> getFilters() {
        return filters;
    }

    /**
     * Sets the filters.
     */
    public void setFilter(List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * Returns the value of a property that is specific to the source type.
     *
     * @param name The property name.
     * @return The property value.
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String name) {
        return (T) properties.get(name);
    }

    /**
     * Returns a map of all properties that are specific to the source type.
     *
     * @return A map of all properties that are specific to the source type.
     */
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the value of a property that is specific to the source type.
     */
    @JsonAnySetter
    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }
}
