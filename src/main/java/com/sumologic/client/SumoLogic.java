package com.sumologic.client;

import com.sumologic.client.collectors.model.*;
import com.sumologic.client.searchsession.model.CancelSearchSessionResponse;
import com.sumologic.client.searchsession.model.GetMessagesForSearchSessionResponse;
import com.sumologic.client.searchsession.model.GetRecordsForSearchSessionResponse;
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
     * Convenience method: takes a query string as argument.
     *
     * @param query The sumo log query string.
     * @return The search response.
     */
    SearchResponse search(String query);

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
     * Returns search session result records.
     *
     * @param searchSessionId The search session ID.
     * @param offset          The offset.
     * @param length          The length.
     * @return The messages.
     */
    GetRecordsForSearchSessionResponse getRecordsForSearchSession(
            String searchSessionId, int offset, int length);

    /**
     * Cancels a search session.
     *
     * @param searchSessionId The search session ID
     * @return
     */
    CancelSearchSessionResponse cancelSearchSession(String searchSessionId);

    /**
     * Gets all available Sumo Logic collectors matching the request.
     *
     * @param request The request
     * @return The response
     */
    GetCollectorsResponse getCollectors(GetCollectorsRequest request);

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The response
     */
    GetCollectorsResponse getCollectors();

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    GetCollectorResponse getCollector(GetCollectorRequest request);

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    GetCollectorResponse getCollector(Long id);

    /**
     * Updates a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    UpdateCollectorResponse updateCollector(UpdateCollectorRequest request);

    /**
     * Convenience method: takes a collector as argument.
     *
     * @param collector The collector
     * @return The response
     */
    UpdateCollectorResponse updateCollector(Collector collector);

    /**
     * Deletes a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    DeleteCollectorResponse deleteCollector(DeleteCollectorRequest request);

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    DeleteCollectorResponse deleteCollector(Long id);

    /**
     * Gets all sources for a Sumo Logic collector matching the request.
     *
     * @param request The request
     * @return The response
     */
    GetSourcesResponse getSources(GetSourcesRequest request);

    /**
     * Convenience method: takes a collector id as argument.
     *
     * @param collectorId The collector id
     * @return The response
     */
    GetSourcesResponse getSources(Long collectorId);

    /**
     * Gets a single source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    GetSourceResponse getSource(GetSourceRequest request);

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id
     * @param sourceId The source id
     * @return The response
     */
    GetSourceResponse getSource(Long collectorId, Long sourceId);

    /**
     * Creates a source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    CreateSourceResponse createSource(CreateSourceRequest request);

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id
     * @param source The source
     * @return The response
     */
    CreateSourceResponse createSource(Long collectorId, Source source);

    /**
     * Updates a source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    UpdateSourceResponse updateSource(UpdateSourceRequest request);

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id
     * @param source The source
     * @return The response
     */
    UpdateSourceResponse updateSource(Long collectorId, Source source);

    /**
     * Deletes a source from a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    DeleteSourceResponse deleteSource(DeleteSourceRequest request);

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id
     * @param sourceId The source id
     * @return The response
     */
    DeleteSourceResponse deleteSource(Long collectorId, Long sourceId);
}
