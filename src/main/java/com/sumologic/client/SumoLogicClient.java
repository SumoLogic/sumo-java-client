package com.sumologic.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sumologic.client.model.HttpGetRequest;
import com.sumologic.client.searchsession.*;
import com.sumologic.client.searchsession.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.JacksonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.search.SearchClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Sumo Logic API client implementation.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @author Christian Beedgen
 */
public class SumoLogicClient implements SumoLogic {

    // Instance fields.

    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private int port = 443;
    private Credentials credentials;
    private static JsonFactory jsonFactory = new JsonFactory();

    private SearchClient searchClient = new SearchClient();
    private CollectorsClient collectorsClient = new CollectorsClient();
    private SearchSessionClient searchSessionClient = new SearchSessionClient();

    // Implementation.

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
     * @param email    Your email.
     * @param password Your password.
     */
    public SumoLogicClient(String email, String password) {
        this.credentials = new Credentials(email, password);
    }

    /**
     * Sets a custom Sumo Logic API url, i.e.,
     * different from https://api.sumologic.com
     *
     * @param urlString The custom sumo logic api URL.
     * @throws MalformedURLException On URL syntax error.
     */
    public void setURL(String urlString) throws MalformedURLException {
        URL url = new URL(urlString);
        this.hostname = url.getHost();
        this.port = (url.getPort() == -1) ?
                (url.getDefaultPort() == -1 ? 80 : url.getDefaultPort()) : url.getPort();
        this.protocol = url.getProtocol();
    }

    //
    // One-shot search.
    //

    /**
     * Issues a search query using Sumo Logic's web service.
     *
     * @param request The search query.
     * @return The resulting log messages.
     */
    @Override
    public SearchResponse search(SearchRequest request) {
        return searchClient.search(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a query string as argument.
     *
     * @param query The sumo log query string.
     * @return The search response.
     */
    public SearchResponse search(String query) {
        return search(new SearchRequest(query));
    }

    //
    // Session-based search.
    //

    /**
     * Start a search session and receive a session ID for subsequent
     * polling of the search status.
     *
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     * @return The search session ID.
     */
    @Override
    public String createSearchSession(
            String query, String fromExpression, String toExpression, String timeZone) {
        CreateSearchSessionRequest createSearchSessionRequest =
                new CreateSearchSessionRequest(query, fromExpression, toExpression, timeZone);
        return searchSessionClient.createSearchSession(
                getConnectionConfig(), createSearchSessionRequest);
    }

    /**
     * Returns the current status of a search session.
     *
     * @param searchSessionId The search session ID
     * @return The status.
     */
    @Override
    public GetSearchSessionStatusResponse getSearchSessionStatus(String searchSessionId) {
        GetSearchSessionStatusRequest getSearchSessionStatusRequest =
                new GetSearchSessionStatusRequest(searchSessionId);
        return searchSessionClient.getSearchSessionStatus(
                getConnectionConfig(), getSearchSessionStatusRequest);
    }

    /**
     * Returns search session result messages.
     *
     * @param searchSessionId The search session ID.
     * @param offset          The offset.
     * @param length          The length.
     * @return The messages.
     */
    @Override
    public GetMessagesForSearchSessionResponse getMessagesForSearchSession(
            String searchSessionId, int offset, int length) {
        GetMessagesForSearchSessionRequest getMessagesForSearchSessionRequest =
                new GetMessagesForSearchSessionRequest(searchSessionId, offset, length);
        return searchSessionClient.getMessagesForSearchSession(
                getConnectionConfig(), getMessagesForSearchSessionRequest);
    }

    //
    // Collectors.
    //

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

    // Private Implementation.

    private DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(hostname, port),
                new UsernamePasswordCredentials(
                        credentials.getEmail(),
                        credentials.getPassword()));
        return httpClient;
    }
}
