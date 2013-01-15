package com.sumologic.client.searchsession.model;

import java.util.Map;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchSessionRecord {

    // Instance fields.

    private Map<String, String> map;

    // Implementation.

    /**
     * Returns whether the specified field exists.
     *
     * @param fieldName The field name.
     * @return Whether the specified field exists.
     */
    public boolean hasField(String fieldName) {
        return map.containsKey(fieldName);
    }

    /**
     * Returns the value for the specified field, or null.
     *
     * @param fieldName The field name.
     * @return The value of the field, or null.
     */
    public String getFieldValue(String fieldName) {
        return map.get(fieldName);
    }

    /**
     * Returns the field value for the specified field as a int.
     */
    public long getFieldValueAsInt(String fieldName) {
        return Integer.parseInt(getFieldValue(fieldName));
    }

    /**
     * Returns the field value for the specified field as a long.
     */
    public long getFieldValueAsLong(String fieldName) {
        return Long.parseLong(getFieldValue(fieldName));
    }

    /**
     * Returns the key-value map.
     *
     * @return The map.
     */
    public Map<String, String> getMap() {
        return map;
    }

    /**
     * Sets the key-value map.
     *
     * @param map The key-value map.
     */
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    /**
     * Sets the key-value map.
     *
     * @param map The key-value map.
     * @return This object.
     */
    public SearchSessionRecord withMap(Map<String, String> map) {
        setMap(map);
        return this;
    }
}
