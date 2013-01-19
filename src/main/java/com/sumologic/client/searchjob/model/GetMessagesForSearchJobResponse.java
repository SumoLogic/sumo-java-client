package com.sumologic.client.searchjob.model;

import java.util.List;

import com.sumologic.client.model.LogMessage;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetMessagesForSearchJobResponse {

    // Instance fields.

    private List<SearchJobField> fields;
    private List<LogMessage> messages;

    // Implementation.

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
    public GetMessagesForSearchJobResponse withFields(List<SearchJobField> fields) {
        setFields(fields);
        return this;
    }

    /**
     * Get the messages.
     *
     * @return The messages.
     */
    public List<LogMessage> getMessages() {
        return messages;
    }

    /**
     * Sets the messages.
     *
     * @param messages The messages.
     */
    public void setMessages(List<LogMessage> messages) {
        this.messages = messages;
    }

    /**
     * Sets the messages
     *
     * @param messages The messages.
     * @return This object.
     */
    public GetMessagesForSearchJobResponse withMessages(List<LogMessage> messages) {
        setMessages(messages);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(128);
        result.append("fields: ");
        result.append(fields);
        result.append(", message count: '");
        result.append(messages.size());
        result.append("'");
        return result.toString();
    }
}
