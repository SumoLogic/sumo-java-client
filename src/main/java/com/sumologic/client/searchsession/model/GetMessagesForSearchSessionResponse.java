package com.sumologic.client.searchsession.model;

import java.util.List;

import com.sumologic.client.model.LogMessage;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class GetMessagesForSearchSessionResponse {

    // Instance fields.

    private List<SearchSessionField> fields;
    private List<LogMessage> messages;

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
    public GetMessagesForSearchSessionResponse withFields(List<SearchSessionField> fields) {
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
     *
     * @param messages
     * @return
     */
    public GetMessagesForSearchSessionResponse withMessages(List<LogMessage> messages) {
        setMessages(messages);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("fields: ");
        result.append(fields);
        result.append(", message count: '");
        result.append(messages.size());
        result.append("'");
        return result.toString();
    }
}
