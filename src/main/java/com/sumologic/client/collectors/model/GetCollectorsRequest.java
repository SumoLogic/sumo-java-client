package com.sumologic.client.collectors.model;

import com.sumologic.client.UrlParameters;
import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * A request to get collectors from the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetCollectorsRequest implements HttpGetRequest {

    private Long limit;
    private Long offset;

    /**
     * Returns the limit.
     *
     * @return The limit.
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * Sets the limit.
     *
     * @return This object.
     */
    public GetCollectorsRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /**
     * Returns the offset.
     *
     * @return The offset.
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Sets the offset.
     *
     * @return This object.
     */
    public GetCollectorsRequest withOffset(Long offset) {
        setOffset(offset);
        return this;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (limit != null) {
            params.add(new BasicNameValuePair(UrlParameters.LIMIT, String.valueOf(limit)));
        }
        if (offset != null) {
            params.add(new BasicNameValuePair(UrlParameters.OFFSET, String.valueOf(offset)));
        }
        return params;
    }
}
