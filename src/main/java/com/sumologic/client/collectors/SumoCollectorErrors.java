package com.sumologic.client.collectors;

import com.sumologic.client.SumoServerError;

/**
 * Errors that might occur during collector API requests.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public enum SumoCollectorErrors implements SumoServerError {
    INVALID_COLLECTOR("collector.invalid"),
    INVALID_SOURCE("source.invalid");

    private String id;

    private SumoCollectorErrors(String id) {
        this.id = "collectors." + id;
    }

    public String getId() {
        return id;
    }
}
