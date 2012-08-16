package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResult;

/**
 * Provides an interface for accessing Sumo Logic's log web service.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public interface SumoLogs {

    /**
     * Issues a search query using the Sumo Logic's search engine.
     *
     * @param searchQuery The search query to be issued
     * @return The search result
     * @throws SumoException If any errors encountered on the sumo logic server/client
     */
    SearchResult search(SearchQuery searchQuery) throws SumoException;
}
