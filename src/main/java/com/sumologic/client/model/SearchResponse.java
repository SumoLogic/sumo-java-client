package com.sumologic.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The search response is an array of log messages with some additional
 * information, e.g., the search query URI.
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public final class SearchResponse {

    private SearchRequest request;
    private List<LogMessage> messages;

    /**
     * Constructs a search response.
     *
     * @param request The original request.
     *
     */
    public SearchResponse(SearchRequest request) {
        this(request, new ArrayList<LogMessage>());
    }

    /**
     * Constructs a search response.
     *
     * @param request The original request.
     * @param messages The messages returned by the search.
     */
    public SearchResponse(SearchRequest request, List<LogMessage> messages) {
        this.request = request;
        this.messages = messages;
    }

    /**
     * Returns the used search query.
     *
     * @return The used search query.
     */
    public final SearchRequest getQuery() {
        return request;
    }

    /**
     * Returns the log messages.
     *
     * @return The log messages.
     */
    public final List<LogMessage> getMessages() {
        return messages;
    }

    /**
     * Returns the number of log messages.
     *
     * @return The number of log messages.
     */
    public final int size() {
        return messages.size();
    }

    /**
     * Returns the raw log lines assembled in one string
     *
     * @return The raw log lines of this result.
     */
    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        for (LogMessage msg: messages) builder.append(msg.toString()+"\n");
        return builder.toString();
    }
}
