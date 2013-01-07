package com.sumologic.client;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.GetCollectorRequest;
import com.sumologic.client.collectors.model.GetCollectorResponse;
import com.sumologic.client.collectors.model.GetCollectorsRequest;
import com.sumologic.client.collectors.model.GetCollectorsResponse;
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
    private int port = 443;
    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private Credentials credentials;

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
        return SearchClient.search(protocol, hostname, port, credentials, request);
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
     * @return The collectors response
     */
    public GetCollectorsResponse getCollectors(GetCollectorsRequest request) {
        return CollectorsClient.get(protocol, hostname, port, credentials, request);
    }

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The collectors response
     */
    public GetCollectorsResponse getCollectors() {
        return getCollectors(new GetCollectorsRequest());
    }

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request
     * @return The collector response
     */
    public GetCollectorResponse getCollector(GetCollectorRequest request) {
        return CollectorsClient.get(protocol, hostname, port, credentials, request);
    }

    /**
     * Convenience method: takes an ID as argument.
     *
     * @param id The ID
     * @return The collector response
     */
    public GetCollectorResponse getCollector(Long id) {
        return getCollector(new GetCollectorRequest(id));
    }
}
