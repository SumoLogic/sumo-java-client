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

    public GetMessagesForSearchSessionRequest(String id, int offset, int length) {
        this.id = id;
        this.offset = offset;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

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
