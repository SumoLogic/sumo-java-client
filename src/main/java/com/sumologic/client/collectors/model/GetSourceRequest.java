package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get a single source for a collector in the Sumo Logic system.
 *
 * @author Jeffrey Wang
 */
public class GetSourceRequest implements HttpGetRequest {

    private Long collectorId;
    private Long sourceId;

    public GetSourceRequest(Long collectorId, Long sourceId) {
        this.collectorId = collectorId;
        this.sourceId = sourceId;
    }

    /**
     * Returns the collector id.
     *
     * @return The collector id.
     */
    public Long getCollectorId() {
        return collectorId;
    }

    /**
     * Returns the source id.
     *
     * @return The source id.
     */
    public Long getSourceId() {
        return sourceId;
    }

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
