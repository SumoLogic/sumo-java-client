package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get sources for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetSourcesRequest implements HttpGetRequest {

    private Long collectorId;

    public GetSourcesRequest(Long collectorId) {
        this.collectorId = collectorId;
    }

    /**
     * Returns the collector id.
     *
     * @return The collector id.
     */
    public Long getCollectorId() {
        return collectorId;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
