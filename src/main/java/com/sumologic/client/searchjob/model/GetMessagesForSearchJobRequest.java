package com.sumologic.client.searchjob.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.sumologic.client.model.HttpGetRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class GetMessagesForSearchJobRequest implements HttpGetRequest {

    // Instance fields.

    private String id;
    private int offset;
    private int limit;

    // Implementation.

    /**
     * Creates a new messages for search job request.
     *
     * @param id     The search job ID.
     * @param offset The offset.
     * @param limit The limit.
     */
    public GetMessagesForSearchJobRequest(String id, int offset, int limit) {
        this.id = id;
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * Return the search job ID.
     *
     * @return The search job ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the search job ID.
     *
     * @param id The search job ID.
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
     * Returns the limit.
     *
     * @return The limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit The limit.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    // HttpGetRequest implementation.

    @Override
    public List<NameValuePair> toUrlParams() {
        List<NameValuePair> result = new ArrayList<NameValuePair>(2);
        result.add(new BasicNameValuePair("offset", Integer.toString(offset)));
        result.add(new BasicNameValuePair("limit", Integer.toString(limit)));
        return result;
    }
}
