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
public final class SearchResult {

    /**
     * Constructs a search response.
     *
     * @param query The search query that has been used .
     */
    public SearchResult(SearchQuery query ) {
        this.query = query;
        this.messages = new ArrayList<LogMessage>();
    }

    /**
     * Returns the used search query.
     *
     * @return The used search query.
     */
    public final SearchQuery getQuery() {
        return query;
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
        StringBuffer buf = new StringBuffer();
        for (LogMessage msg: messages) buf.append(msg.toString()+"\n");
        return buf.toString();
    }

    private ArrayList<LogMessage> messages;
    private SearchQuery query;
}
