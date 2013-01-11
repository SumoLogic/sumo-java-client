package com.sumologic.client;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.search.SearchClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The sumo client implementation.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public class SumoLogicClient implements SumoLogic {

    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private int port = 443;
    private Credentials credentials;

    private SearchClient searchClient = new SearchClient();
    private CollectorsClient collectorsClient = new CollectorsClient();

    /**
     * Constructs a Sumo Logic client.
     *
     * @param credentials The credential used to access sumo logic's web service.
     */
    public SumoLogicClient(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Convenience: constructs the credentials using email and password.
     *
     * @param email    Your email
     * @param password Your password
     */
    public SumoLogicClient(String email, String password) {
        this.credentials = new Credentials(email, password);
    }

    /**
     * Sets a custom Sumo Logic API url, i.e.,
     * different from https://api.sumologic.com
     *
     * @param urlString The custom sumo logic api URL
     * @throws MalformedURLException On URL syntax error
     */
    public void setURL(String urlString) throws MalformedURLException {
        URL url = new URL(urlString);
        this.hostname = url.getHost();
        this.port = (url.getPort() == -1) ?
                (url.getDefaultPort() == -1 ? 80 : url.getDefaultPort()) : url.getPort();
        this.protocol = url.getProtocol();
    }

    /**
     * Issues a search query using Sumo Logic's web service.
     *
     * @param request The search Query
     * @return The resulting log messages
     */
    public SearchResponse search(SearchRequest request) {
        return searchClient.search(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a query string as argument.
     *
     * @param query The sumo log query string
     * @return The search response
     */
    public SearchResponse search(String query) {
        return search(new SearchRequest(query));
    }

    /**
     * Gets all available Sumo Logic collectors matching the request.
     *
     * @param request The request
     * @return The response
     */
    public GetCollectorsResponse getCollectors(GetCollectorsRequest request) {
        return collectorsClient.get(getConnectionConfig(), request);
    }

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The response
     */
    public GetCollectorsResponse getCollectors() {
        return getCollectors(new GetCollectorsRequest());
    }

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public GetCollectorResponse getCollector(GetCollectorRequest request) {
        return collectorsClient.get(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    public GetCollectorResponse getCollector(Long id) {
        return getCollector(new GetCollectorRequest(id));
    }

    /**
     * Updates a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public UpdateCollectorResponse updateCollector(UpdateCollectorRequest request) {
        return collectorsClient.update(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a collector as argument.
     *
     * @param collector The collector
     * @return The response
     */
    public UpdateCollectorResponse updateCollector(Collector collector) {
        return updateCollector(new UpdateCollectorRequest(collector.getId(), collector));
    }

    /**
     * Deletes a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public DeleteCollectorResponse deleteCollector(DeleteCollectorRequest request) {
        return collectorsClient.delete(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    public DeleteCollectorResponse deleteCollector(Long id) {
        return deleteCollector(new DeleteCollectorRequest(id));
    }

    /**
     * Gets all sources for a Sumo Logic collector matching the request.
     *
     * @param request The request
     * @return The response
     */
    public GetSourcesResponse getSources(GetSourcesRequest request) {
        return collectorsClient.getSources(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a collector id as argument.
     *
     * @param collectorId The collector id
     * @return The response
     */
    public GetSourcesResponse getSources(Long collectorId) {
        return getSources(new GetSourcesRequest(collectorId));
    }

    /**
     * Gets a single source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public GetSourceResponse getSource(GetSourceRequest request) {
        return collectorsClient.getSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id
     * @param sourceId The source id
     * @return The response
     */
    public GetSourceResponse getSource(Long collectorId, Long sourceId) {
        return getSource(new GetSourceRequest(collectorId, sourceId));
    }

    /**
     * Creates a source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public CreateSourceResponse createSource(CreateSourceRequest request) {
        return collectorsClient.createSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id
     * @param source The source
     * @return The response
     */
    public CreateSourceResponse createSource(Long collectorId, Source source) {
        return createSource(new CreateSourceRequest(collectorId, source));
    }

    /**
     * Updates a source for a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public UpdateSourceResponse updateSource(UpdateSourceRequest request) {
        return collectorsClient.updateSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id
     * @param source The source
     * @return The response
     */
    public UpdateSourceResponse updateSource(Long collectorId, Source source) {
        return updateSource(new UpdateSourceRequest(collectorId, source.getId(), source));
    }

    /**
     * Deletes a source from a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public DeleteSourceResponse deleteSource(DeleteSourceRequest request) {
        return collectorsClient.deleteSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id
     * @param sourceId The source id
     * @return The response
     */
    public DeleteSourceResponse deleteSource(Long collectorId, Long sourceId) {
        return deleteSource(new DeleteSourceRequest(collectorId, sourceId));
    }

    private ConnectionConfig getConnectionConfig() {
        return new ConnectionConfig(protocol, hostname, port, credentials);
    }
}
