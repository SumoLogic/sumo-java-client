package com.sumologic.client.model;

import com.sumologic.client.UrlParameters;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A log search query in sumo logic's web service.
 * <p/>
 * The query URI comprises the query string, the length of time the query should address,
 * time zone, pagination parameters, the order of the results to be returned.
 * The default search query string is a wildcard search querying all log lines
 * Refer to the setters for specific format descriptions.</p>
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public final class SearchRequest {
    private String query;
    private Date fromTime = null;
    private Date toTime = null;
    private long limit = 0;
    private long offset = 0;
    private SearchOrder order = SearchOrder.DEFAULT;
    private String timeZone = "";

    /**
     * Constructs a wildcard query that seeks to return all log lines.
     */
    public SearchRequest() {
        this("*");
    }

    /**
     * Constructs a search query using the given query string.
     *
     * @param queryString The search query string
     */
    public SearchRequest(String queryString) {
        this.query = queryString;
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
    public SearchRequest withQuery(String query) {
        setQuery( query );
        return this;
    }

    /**
     * Returns the from time
     *
     * @return The from time.
     */
    public Date getFromTime() {
        return fromTime;
    }

    /**
     * Sets the from timestamp.
     */
    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }
    /**
     * Sets the from timestamp.
     *
     * @return This object.
     */
    public SearchRequest withFromTime(Date fromTime) {
        setFromTime(fromTime);
        return this;
    }

    /**
     * Returns the "to" time
     *
     * @return The "to" time.
     */
    public Date getToTime() {
        return toTime;
    }

    /**
     * Sets the "to" timestamp.
     */
    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    /**
     * Sets the "to" timestamp.
     *
     * @return This object.
     */
    public SearchRequest withToTime(Date toTime) {
        setToTime(toTime);
        return this;
    }

    /**
     * Returns the timezone.
     *
     * @return The timezone.
     */
    public String getTimezone() {
        return timeZone;
    }

    /**
     * Sets the time-zone.
     */
    public void setTimezone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Sets the time-zone.
     *
     * @return This object.
     */
    public SearchRequest withTimezone(String timeZone) {
        setTimezone(timeZone);
        return this;
    }

    /**
     * Sets the pagination offset.
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * Sets the pagination offset.
     *
     * @return This object.
     */
    public SearchRequest withOffset(long offset) {
        setOffset(offset);
        return this;
    }

    /**
     * Returns the pagination offset.
     *
     * @return The pagination offset.
     */
    public long getOffset() {
        return this.offset;
    }

    /**
     * Sets the pagination limit.
     */
    public void setLimit(long limit) {
        this.limit = limit;
    }

    /**
     * Sets the pagination limit.
     *
     * @return This object.
     */
    public SearchRequest withLimit(long limit) {
        setLimit(limit);
        return this;
    }

    /**
     * Returns the pagination limit.
     *
     * @return The pagination limit.
     */
    public long getLimit() {
        return this.limit;
    }

    /**
     * Sets the result order.
     */
    public void setOrder(SearchOrder order) {
        this.order = order;
    }

    /**
     * Sets the result order.
     *
     * @return This object.
     */
    public SearchRequest withOrder(SearchOrder order) {
        setOrder(order);
        return this;
    }

    /**
     * Returns the result order.
     *
     * @return The result order.
     */
    public SearchOrder getOrder() {
        return this.order;
    }

    /**
     * Converts the query to a valid search URL
     *
     * @return A valid search URL
     */
    public String toString() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(UrlParameters.SEARCH_QUERY, query));
        if (fromTime != null) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_FROM, String.valueOf(fromTime.getTime())));
        }
        if (toTime != null) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_TO, String.valueOf(toTime.getTime())));
        }
        if (!timeZone.isEmpty()) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_TIMEZONE, timeZone));
        }
        if (order != SearchOrder.DEFAULT) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_ORDER, order.parameterValue()));
        }
        if (offset != 0) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_OFFSET, String.valueOf(offset)));
        }
        if (limit != 0) {
            params.add(new BasicNameValuePair(UrlParameters.SEARCH_LIMIT, String.valueOf(limit)));
        }
        return URLEncodedUtils.format(params, "UTF-8");
    }
}
