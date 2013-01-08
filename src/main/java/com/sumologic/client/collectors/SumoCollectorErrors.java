package com.sumologic.client.collectors;

import com.sumologic.client.SumoServerError;

/**
 * Errors that might occur during collector API requests.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public enum SumoCollectorErrors implements SumoServerError {
    INVALID_COLLECTOR("invalid.collector"),
    INVALID_BLADE("invalid.blade");

    private String id = "";

    SumoCollectorErrors(String id) {
        this.id = "collector." + id;
    }

    public String getId() {
        return id;
    }
}
