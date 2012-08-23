package com.sumologic.client.model;

import com.sumologic.client.Headers;
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
 * The default search query sting is a wildcard search querying all log lines
 * in JSON format. Refer to the setters for specific format descriptions.</p>
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public final class SearchQuery {

    /**
     * Constructs a wildcard query that seeks to return all log lines.
     */
    public SearchQuery() {
    }

    /**
     * Constructs a search query using the given query string.
     * For the format of the query string please consult the
     * sumo logic's manual. TODO: add link
     *
     * @param queryString The search query string
     */
    public SearchQuery(String queryString) {
        this.query = queryString;
    }

    /**
     * Returns the query string.
     *
     * @return The query string.
     */
    public String getQueryString() {
        return query;
    }

    /**
     * Sets the query string.
     *
     * @return This object.
     */
    public SearchQuery setQueryString(String query) {
        this.query = query;
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
     *
     * @return This object.
     */
    public SearchQuery setFromTime(Date fromTime) {
        this.fromTime = fromTime;
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
     *
     * @return This object.
     */
    public SearchQuery setToTime(Date toTime) {
        this.toTime = toTime;
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
     *
     * @return This object.
     */
    public SearchQuery setTimezone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Sets the pagination offset.
     *
     * @return This object.
     */
    public SearchQuery setOffset(long offset) {
        this.offset = offset;
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
     *
     * @return This object.
     */
    public SearchQuery setLimit(long limit) {
        this.limit = limit;
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
     *
     * @return This object.
     */
    public SearchQuery setOrder(SearchOrder order) {
        this.order = order;
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
        params.add(new BasicNameValuePair(Headers.SEARCH_QUERY, query));
        if (fromTime != null)
            params.add(new BasicNameValuePair(Headers.SEARCH_FROM, String.valueOf(fromTime.getTime())));
        if (toTime != null)
            params.add(new BasicNameValuePair(Headers.SEARCH_TO, String.valueOf(toTime.getTime())));
        if (!timeZone.isEmpty())
            params.add(new BasicNameValuePair(Headers.SEARCH_TIMEZONE, timeZone));
        if (order != SearchOrder.DEFAULT)
            params.add(new BasicNameValuePair(Headers.SEARCH_ORDER, order.parameterValue()));
        if (offset != 0)
            params.add(new BasicNameValuePair(Headers.SEARCH_OFFSET, String.valueOf(offset)));
        if (limit != 0)
            params.add(new BasicNameValuePair(Headers.SEARCH_LIMIT, String.valueOf(limit)));

        return URLEncodedUtils.format(params, "UTF-8");
    }

    private String query = "*";
    private Date fromTime = null;
    private Date toTime = null;
    private long limit = 0;
    private long offset = 0;
    private SearchOrder order = SearchOrder.DEFAULT;
    private String timeZone = "";
}
