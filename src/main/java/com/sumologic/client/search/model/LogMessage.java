package com.sumologic.client.search.model;

import com.sumologic.client.UrlParameters;

import java.util.*;

/**
 * This class represents a sumo logic log message which is basically
 * a map of keys and values of type string.
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public class LogMessage {

    private Map<String, String> map;

    /**
     * Constructs a log message (usually done by the client).
     *
     * @param map The fields of the log message.
     */
    public LogMessage(Map<String, String> map) {
        this.map = map;
    }

    /**
     * Returns the names of the fields.
     *
     * @return A set of field names.
     */
    public final Set<String> getFieldNames() {
      return Collections.unmodifiableSet(map.keySet());
    }

    /**
     * Returns a text field to the given key
     *
     * @param key The key of the field
     * @return A string
     */
    public final String stringField(String key) {
        return map.get(key);
    }

    /**
     * Returns a long number field to the given key
     *
     * @param key The key of the field
     * @return A long number
     * @throws NumberFormatException Thrown, if field does not contain a long number.
     */
    public final long longField(String key) throws NumberFormatException {
        return Long.parseLong(map.get(key));
    }

    /**
     * Returns a double number field to the given key
     *
     * @param key The key of the field
     * @return A long number
     * @throws NumberFormatException Thrown, if field does not contain a long number.
     */
    public final double doubleField(String key) throws NumberFormatException {
        return Double.parseDouble(map.get(key));
    }

    /**
     * Returns a Date object by interpreting the field to the given key
     * interpreted as UTC long time
     *
     * @param key The key of the field
     * @return A date object
     * @throws NumberFormatException Thrown, if field does not contain a long number.
     */
    public final Date dateField(String key) throws NumberFormatException {
        return new Date(longField(key));
    }

    /**
     * Returns the raw log line human-readable string.
     *
     * @return The raw log line human-readable string.
     */
    public final String getLogLine() {
        return stringField(UrlParameters.LOG_MESSAGE_RAW);
    }


    /**
     * Returns the log message time
     *
     * @return The log message time
     */
    public final Date getTime() {
        return dateField(UrlParameters.LOG_MESSAGE_TIME);
    }

    /**
     * Returns the log message receipt time
     *
     * @return The log message receipt time
     */
    public final Date getReceiptTime() {
        return dateField(UrlParameters.LOG_MESSAGE_RECEIPT_TIME);
    }

    /**
     * Returns the log message source host
     *
     * @return The log message source host
     */
    public final String getSourceHost() {
        return stringField(UrlParameters.LOG_MESSAGE_SOURCE_HOST);
    }


    /**
     * Returns the log message source category
     *
     * @return The log message source category
     */
    public final String getSourceCategory() {
        return stringField(UrlParameters.LOG_MESSAGE_SOURCE_CATEGORY);
    }

    /**
     * Returns the log message source name
     *
     * @return The log message source name
     */
    public final String getSourceName() {
        return stringField(UrlParameters.LOG_MESSAGE_SOURCE_NAME);
    }

    /**
     * Returns log message line count when results are aggregated.
     *
     * @return log message line count when results are aggregated.
     */
    public final long getLineCount() {
        return longField(UrlParameters.LOG_MESSAGE_COUNT);
    }

    /**
     * Returns the log message signature
     *
     * @return The log message signature
     */
    public final String getSignature() {
        return stringField(UrlParameters.LOG_MESSAGE_SIGNATURE);
    }

    /**
     * Returns the raw text log line.
     *
     * @return The raw text log line.
     */
    @Override
    public String toString() {
        return getLogLine();
    }

}
