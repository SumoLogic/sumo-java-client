package com.sumologic.client.collectors;

import com.sumologic.client.SumoServerError;

/**
 * Errors that might occur during collector API requests.
 *
 * @author Jeffrey Wang
 */
public enum SumoCollectorErrors implements SumoServerError {
    INVALID_COLLECTOR("collector.invalid"),
    INVALID_SOURCE("source.invalid"),
    CANNOT_MODIFY_COLLECTOR("collector.forbidden"),
    BAD_COLLECTOR_ID("collector.request.id.invalid"),
    BAD_SOURCE_ID("source.request.id.invalid"),
    DUPLICATE_RESOURCE_NAME("validation.name.duplicate"),
    MISSING_FIELDS("validation.fields.missing"),
    INVALID_FIELD_VALUE("validation.fields.invalid");

    private String id;

    private SumoCollectorErrors(String id) {
        this.id = "collectors." + id;
    }

    public String getId() {
        return id;
    }
}
