package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get a single collector from the sumo logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetCollectorRequest implements HttpGetRequest {

    private Long id;

    public GetCollectorRequest(Long id) {
        this.id = id;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
