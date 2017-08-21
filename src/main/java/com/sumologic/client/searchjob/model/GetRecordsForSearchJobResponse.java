package com.sumologic.client.searchjob.model;


import java.util.List;

public class GetRecordsForSearchJobResponse {

    private List<SearchJobField> fields;
    private List<SearchJobRecord> records;

    /**
     * Returns the fields.
     *
     * @return The fields.
     */
    public List<SearchJobField> getFields() {
        return fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     */
    public void setFields(List<SearchJobField> fields) {
        this.fields = fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     * @return This object.
     */
    public GetRecordsForSearchJobResponse withFields(List<SearchJobField> fields) {
        setFields(fields);
        return this;
    }

    /**
     * Get the records.
     *
     * @return The records.
     */
    public List<SearchJobRecord> getRecords() {
        return records;
    }

    /**
     * Sets the records.
     *
     * @param records The records.
     */
    public void setRecords(List<SearchJobRecord> records) {
        this.records = records;
    }

    /**
     * Sets the records.
     *
     * @param messages The records.
     * @return This object.
     */
    public GetRecordsForSearchJobResponse withMessages(List<SearchJobRecord> messages) {
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
