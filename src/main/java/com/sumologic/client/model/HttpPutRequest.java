package com.sumologic.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Common interface for HTTP PUT requests.
 */
public interface HttpPutRequest {

    @JsonIgnore
    public String getETag();
}
