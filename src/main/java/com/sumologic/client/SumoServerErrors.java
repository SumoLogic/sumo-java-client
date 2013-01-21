package com.sumologic.client;

/**
 * General sumo API web service errors.
 *
 * @author Sebastian Mies
 */
public enum SumoServerErrors implements SumoServerError {
    INTERNAL_SERVER_ERROR("internal.error"),
    UNAUTHORIZED("unauthorized"),
    UNAVAILABLE("service.unavailable"),
    NOT_FOUND("notfound"),
    UNSUPPORTED_METHOD("method.unsupported"),
    INVALID_CONTENT_TYPE("contenttype.invalid"),

    ETAG_MISMATCH("service.etagid.mismatch"),
    MISSING_PRECONDITION("service.ifthen.missing"),
    BAD_PRECONDITION("service.ifthen.notquoted"),

    INVALID_LIMIT("service.limit.invalid"),
    INVALID_OFFSET("service.offset.invalid"),
    INVALID_JSON("service.json.invalid");

    private String id = "";

    private SumoServerErrors(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
