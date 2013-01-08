package com.sumologic.client;

import com.sumologic.client.search.model.SearchRequest;
import com.sumologic.client.search.model.SearchResponse;

/**
 * Provides an interface for accessing Sumo Logic's log web service.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public interface SumoLogic {

    /**
     * Issues a search query using the Sumo Logic's search engine.
     *
     * @param searchRequest The search query to be issued
     * @return The search result
     */
    SearchResponse search(SearchRequest searchRequest);
}
