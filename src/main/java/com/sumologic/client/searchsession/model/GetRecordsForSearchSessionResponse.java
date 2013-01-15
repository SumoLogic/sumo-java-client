package com.sumologic.client.searchsession.model;


import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetRecordsForSearchSessionResponse {

    // Instance fields.

    private List<SearchSessionField> fields;
    private List<SearchSessionRecord> records;

    // Implementation.

    /**
     * Returns the fields.
     *
     * @return The fields.
     */
    public List<SearchSessionField> getFields() {
        return fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     */
    public void setFields(List<SearchSessionField> fields) {
        this.fields = fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     * @return This object.
     */
    public GetRecordsForSearchSessionResponse withFields(List<SearchSessionField> fields) {
        setFields(fields);
        return this;
    }

    /**
     * Get the records.
     *
     * @return The records.
     */
    public List<SearchSessionRecord> getRecords() {
        return records;
    }

    /**
     * Sets the records.
     *
     * @param records The records.
     */
    public void setRecords(List<SearchSessionRecord> records) {
        this.records = records;
    }

    /**
     * Sets the records.
     *
     * @param messages The records.
     * @return This object.
     */
    public GetRecordsForSearchSessionResponse withMessages(List<SearchSessionRecord> messages) {
        setRecords(messages);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("fields: ");
        result.append(fields);
        result.append(", records count: '");
        result.append(records.size());
        result.append("'");
        return result.toString();
    }
}
