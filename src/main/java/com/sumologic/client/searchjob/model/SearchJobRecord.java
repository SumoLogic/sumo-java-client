package com.sumologic.client.searchjob.model;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SearchJobRecord {

    private Map<String, String> map;

    /**
     * Returns the names of the fields.
     *
     * @return A set of field names.
     */
    public final Set<String> getFieldNames() {
        return Collections.unmodifiableSet(map.keySet());
    }

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
    public String stringField(String fieldName) {
        return map.get(fieldName);
    }

    /**
     * Returns the value for the specified as an integer.
     *
     * @param fieldName The field name.
     * @return The value for the specified field as an integer.
     */
    public long intField(String fieldName) {
        return Integer.parseInt(stringField(fieldName));
    }

    /**
     * Returns the value for the specified as a long.
     *
     * @param fieldName The field name.
     * @return The value for the specified field as a long.
     */
    public long longField(String fieldName) {
        return Long.parseLong(stringField(fieldName));
    }

    /**
     * Returns the value for the specified as a double.
     *
     * @param fieldName The field name.
     * @return The value for the specified field as a double.
     */
    public double doubleField(String fieldName) {
        return Double.parseDouble(fieldName);
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
    public SearchJobRecord withMap(Map<String, String> map) {
        setMap(map);
        return this;
    }
}
