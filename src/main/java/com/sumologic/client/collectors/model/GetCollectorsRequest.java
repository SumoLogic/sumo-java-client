package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to get collectors from the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetCollectorsRequest implements HttpGetRequest {

    @Override
    public List<NameValuePair> toUrlParams() {
        return Collections.emptyList();
    }
}
