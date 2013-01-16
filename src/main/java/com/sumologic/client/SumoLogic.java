package com.sumologic.client;

import com.sumologic.client.searchsession.model.CancelSearchSessionResponse;
import com.sumologic.client.searchsession.model.CreateSearchSessionRequest;
import com.sumologic.client.searchsession.model.GetMessagesForSearchSessionResponse;
import com.sumologic.client.searchsession.model.GetSearchSessionStatusResponse;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;

/**
 * Provides an interface for accessing Sumo Logic's log web service.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @author Christian Beedgen
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
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     * @return The search session ID
     */
    String createSearchSession(
            String query, String fromExpression, String toExpression, String timeZone);

    /**
     * Returns the current status of a search session.
     *
     * @param searchSessionId The search session ID
     * @return The status
     */
    GetSearchSessionStatusResponse getSearchSessionStatus(String searchSessionId);

    /**
     * Returns search session result messages.
     *
     * @param searchSessionId The search session ID.
     * @param offset          The offset.
     * @param length          The length.
     * @return The messages.
     */
    GetMessagesForSearchSessionResponse getMessagesForSearchSession(
            String searchSessionId, int offset, int length);

    /**
     * Cancels a search session.
     *
     * @param searchSessionId The search session ID
     * @return
     */
    CancelSearchSessionResponse cancelSearchSession(String searchSessionId);
}
