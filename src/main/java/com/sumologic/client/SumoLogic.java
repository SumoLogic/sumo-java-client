package com.sumologic.client;

import com.sumologic.client.model.CreateSearchSessionRequest;
import com.sumologic.client.model.GetSearchSessionStatusResponse;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;

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

    /**
     * Start a search session and receive a session ID for subsequent
     * polling of the search status.
     *
     * @param searchSessionRequest The search session to be started
     * @return The search session ID
     */
    String createSearchSession(CreateSearchSessionRequest searchSessionRequest);

    GetSearchSessionStatusResponse getSearchSessionStatus(String searchSessionId);


}
