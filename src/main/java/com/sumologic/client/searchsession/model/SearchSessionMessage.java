package com.sumologic.client.searchsession.model;

import java.util.Map;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchSessionMessage {

    // Constants.

    public static final String MESSAGE_TIME = "_messagetime";
    public static final String RECEIPT_TIME = "_receipttime";
    public static final String SOURCE_HOST = "_sourcehost";
    public static final String SOURCE_NAME = "_sourcename";
    public static final String SOURCE_CATEGORY = "_sourcecategory";
    public static final String RAW = "_raw";

    // Instance fields.

    private Map<String, String> map;

    // Implementation.

    /**
     * Returns the message time.
     *
     * @return The message time.
     */
    public long getMessageTime() {
        return getFieldValueAsLong(MESSAGE_TIME);
    }

    /**
     * Returns the receipt time.
     *
     * @return The receipt time.
     */
    public long getReceiptTime() {
        return getFieldValueAsLong(RECEIPT_TIME);
    }

    /**
     * Returns the source host.
     *
     * @return The source host.
     */
    public String getSourceHost() {
        return getFieldValue(SOURCE_HOST);
    }

    /**
     * Returns the source name.
     *
     * @return The source name.
     */
    public String getSourceName() {
        return getFieldValue(SOURCE_NAME);
    }

    /**
     * Returns the source category.
     *
     * @return The source category.
     */
    public String getSourceCategory() {
        return getFieldValue(SOURCE_CATEGORY);
    }

    /**
     * Returns the raw message.
     *
     * @return The raw message.
     */
    public String getRaw() {
        return getFieldValue(RAW);
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
    public SearchSessionMessage withMap(Map<String, String> map) {
        setMap(map);
        return this;
    }
}
