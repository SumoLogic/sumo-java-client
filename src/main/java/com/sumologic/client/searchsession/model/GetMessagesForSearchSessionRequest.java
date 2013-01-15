package com.sumologic.client.searchsession.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetMessagesForSearchSessionRequest implements HttpGetRequest {

    // Instance fields.

    private String id;
    private int offset;
    private int length;

    // Implementation.

    /**
     * Creates a new messages for search session request.
     *
     * @param id The search session ID.
     * @param offset The offset.
     * @param length The length.
     */
    public GetMessagesForSearchSessionRequest(String id, int offset, int length) {
        this.id = id;
        this.offset = offset;
        this.length = length;
    }

    /**
     * Return the search session ID.
     *
     * @return The search session ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the search session ID.
     *
     * @param id The search session ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the offset.
     *
     * @return The offset.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     *
     * @param offset The offset.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Returns the length.
     *
     * @return The length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length.
     *
     * @param length The length.
     */
    public void setLength(int length) {
        this.length = length;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        List<NameValuePair> result = new ArrayList<NameValuePair>(2);
        result.add(new BasicNameValuePair("offset", Integer.toString(offset)));
        result.add(new BasicNameValuePair("length", Integer.toString(length)));
        return result;
    }
}
