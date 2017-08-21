package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get a single collector from the Sumo Logic system.
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
