package com.sumologic.client.searchjob.model;

import com.sumologic.client.model.HttpPostRequest;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public final class CreateSearchJobRequest implements HttpPostRequest {

    // Instance fields.

    private String query;
    private String fromExpression;
    private String toExpression;
    private String timeZone;

    // Implementation.

    /**
     * Creates a search job request.
     *
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     */
    public CreateSearchJobRequest(String query,
                                  String fromExpression,
                                  String toExpression,
                                  String timeZone) {
        this.query = query;
        this.fromExpression = fromExpression;
        this.toExpression = toExpression;
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
     * Returns the from expression.
     *
     * @return The from expression.
     */
    public String getFromExpression() {
        return fromExpression;
    }

    /**
     * Sets the from expression.
     */
    public void setFromExpression(String fromExpression) {
        this.fromExpression = fromExpression;
    }

    /**
     * Sets the from expression.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withFromExpresssion(String fromExpression) {
        setFromExpression(fromExpression);
        return this;
    }

    /**
     * Returns the to expression.
     *
     * @return The to expression.
     */
    public String getToExpression() {
        return toExpression;
    }

    /**
     * Sets the to expression.
     */
    public void setToExpression(String toExpression) {
        this.toExpression = toExpression;
    }

    /**
     * Sets the to expression.
     *
     * @return This object.
     */
    public CreateSearchJobRequest withToTime(String toExpression) {
        setToExpression(toExpression);
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
