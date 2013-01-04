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
    private List<LogMessage> messages;
    private SearchRequest request;

    /**
     * Constructs a search response.
     *
     * @param request The search query that has been used .
     */
    public SearchResponse(SearchRequest request) {
        this.request = request;
        this.messages = new ArrayList<LogMessage>();
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
