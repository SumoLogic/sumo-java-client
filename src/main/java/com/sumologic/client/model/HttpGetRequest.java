package com.sumologic.client.model;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Common interface for HTTP GET requests.
 */
public interface HttpGetRequest {

    List<NameValuePair> toUrlParams();
}
