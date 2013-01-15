package com.sumologic.client.searchsession.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetSearchSessionStatusResponse {

    // Instance fields.

    private String state;
    private int messageCount;
    private int recordCount;
    // TODO: Warnings
    // TODO: Errors

    // Implementation.

    /**
     * Returns the state.
     *
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state The state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the state.
     *
     * @param state The state.
     * @return This object.
     */
    public GetSearchSessionStatusResponse withState(String state) {
        setState(state);
        return this;
    }

    /**
     * Returns the number of messages.
     *
     * @return The number of messages.
     */
    public int getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the number of messages.
     *
     * @param messageCount The number of messages.
     */
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    /**
     * Sets the number of messages.
     *
     * @param messageCount The number of messages.
     * @return This object.
     */
    public GetSearchSessionStatusResponse withMessageCount(int messageCount) {
        setMessageCount(messageCount);
        return this;
    }

    /**
     * Returns the number of records.
     *
     * @return The number of records.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the number of records.
     *
     * @param recordCount The number of records.
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * Sets the number of records.
     *
     * @param recordCount The number of records.
     * @return This object.
     */
    public GetSearchSessionStatusResponse withRecordCount(int recordCount) {
        setRecordCount(recordCount);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("state: '");
        result.append(state);
        result.append("', message count: '");
        result.append(messageCount);
        result.append("', record count: '");
        result.append(recordCount);
        result.append("'");
        return result.toString();
    }
}
