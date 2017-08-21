package com.sumologic.client.searchjob.model;

import com.sumologic.client.model.HttpPostRequest;

public final class CreateSearchJobRequest implements HttpPostRequest {

    private String query;
    private String from;
    private String to;
    private String timeZone;

    /**
     * Creates a search job request.
     *
     * @param query    The query.
     * @param from     The start of the time range.
     * @param to       The end of the time range.
     * @param timeZone The time zone.
     */
    public CreateSearchJobRequest(String query,
                                  String from,
                                  String to,
                                  String timeZone) {
        this.query = query;
        this.from = from;
        this.to = to;
        this.timeZone = timeZone;
    }

    /**
     * Returns the query.
     *
     * @return The query.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query.
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Sets the query.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withQuery(String query) {
        setQuery(query);
        return this;
    }

    /**
     * Returns the start of the time range.
     *
     * @return The start of the time range.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start of the time range.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Sets the start of the time range.
     *
     * @param from The start of the time range.
     * @return This object.
     */
    public CreateSearchJobRequest withFrom(String from) {
        setFrom(from);
        return this;
    }

    /**
     * Returns the end of the time range.
     *
     * @return The end of the time range.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end of the time range.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Sets the end of the time range.
     *
     * @param to The end of the time range.
     * @return This object.
     */
    public CreateSearchJobRequest withTo(String to) {
        setTo(to);
        return this;
    }

    /**
     * Returns the time zone.
     *
     * @return The time zone.
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the time zone.
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Sets the time zone.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withTimeZone(String timeZone) {
        setTimeZone(timeZone);
        return this;
    }
}
