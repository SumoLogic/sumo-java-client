package com.sumologic.client;

/**
 * API errors that might occur during a search.
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public enum SumoSearchErrors implements SumoServerError {
    EMPTY_FIELD_LIST("no.fields"),
    INVALID_TIMESTAMP_TO("invalid.timestamp.to"),
    INVALID_TIMESTAMP_FROM("invalid.timestamp.from"),
    TO_SMALLER_THAN_FROM("to.smaller.than.from"),
    UNKNOWN_TIMEZONE("unknown.timezone"),
    EMPTY_TIMEZONE("empty.timezone"),
    NO_QUERY_STRING("no.q"),
    TIMED_OUT("timed.out"),
    CANCELLED("cancelled"),
    RETURNED_ERRORS("errors"),
    NO_COUNTS("no.counts"),
    QUERY_PARSE_ERROR("parse.error"),
    MESSAGE_RETRIEVAL_ERROR("message.retrieval.error"),
    FORMAT_DOES_NOT_ALLOW_AGGREGATION("aggregates.unsupported"),
    FORMAT_DOES_NOT_ALLOW_RAW("raw.unsupported"),
    OUTPUT_CLOSED("output.closed"),
    FORMAT_UNKNOWN("unknown.format");

    SumoSearchErrors(String id) {
        this.id = "search." + id;
    }

    public String getId() {
        return id;
    }

    private String id = "";
}
