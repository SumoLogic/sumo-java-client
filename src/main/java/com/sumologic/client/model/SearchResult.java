package com.sumologic.client.model;

import com.sumologic.client.LogMessage;
import com.sumologic.client.SumoClientException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private ArrayList<LogMessage> messages;
    private SearchQuery query;
}
