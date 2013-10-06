package com.sumologic.client;

import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonFactory;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.dashboard.DashboardClient;
import com.sumologic.client.dashboard.model.GetDashboardDataRequest;
import com.sumologic.client.dashboard.model.GetDashboardDataResponse;
import com.sumologic.client.dashboard.model.GetDashboardRequest;
import com.sumologic.client.dashboard.model.GetDashboardResponse;
import com.sumologic.client.dashboard.model.GetDashboardsRequest;
import com.sumologic.client.dashboard.model.GetDashboardsResponse;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.search.SearchClient;
import com.sumologic.client.searchjob.*;
import com.sumologic.client.searchjob.model.*;
import com.sumologic.client.util.HttpUtils;

/**
 * The Sumo Logic API client implementation.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @author Christian Beedgen
 */
public class SumoLogicClient implements SumoLogic {

    // Instance fields.
    private HttpUtils httpUtils = new HttpUtils();

    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private int port = 443;
    private Credentials credentials;
    private static JsonFactory jsonFactory = new JsonFactory();

    private SearchClient searchClient = new SearchClient(httpUtils);
    private CollectorsClient collectorsClient = new CollectorsClient(httpUtils);
    private SearchJobClient searchJobClient = new SearchJobClient(httpUtils);
    private DashboardClient dashboardClient = new DashboardClient(httpUtils);

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
     * different from https://api.sumologic.com.
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

    private ConnectionConfig getConnectionConfig() {
        return new ConnectionConfig(protocol, hostname, port, credentials);
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
    // Search jobs.
    //

    /**
     * Starts a search job and receives a job ID for subsequent
     * polling of the search status.
     *
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     * @return The search job ID.
     */
    @Override
    public String createSearchJob(
            String query, String fromExpression, String toExpression, String timeZone) {
        CreateSearchJobRequest createSearchJobRequest =
                new CreateSearchJobRequest(query, fromExpression, toExpression, timeZone);
        return searchJobClient.createSearchJob(
                getConnectionConfig(), createSearchJobRequest);
    }

    /**
     * Returns the current status of a search job.
     *
     * @param searchJobId The search job ID.
     * @return The status.
     */
    @Override
    public GetSearchJobStatusResponse getSearchJobStatus(String searchJobId) {
        GetSearchJobStatusRequest getSearchJobStatusRequest =
                new GetSearchJobStatusRequest(searchJobId);
        return searchJobClient.getSearchJobStatus(
                getConnectionConfig(), getSearchJobStatusRequest);
    }

    /**
     * Returns messages for the specified search job.
     *
     * @param searchJobId The search job ID.
     * @param offset      The offset.
     * @param limit       The length.
     * @return The messages.
     */
    @Override
    public GetMessagesForSearchJobResponse getMessagesForSearchJob(
            String searchJobId, int offset, int limit) {
        GetMessagesForSearchJobRequest getMessagesForSearchJobRequest =
                new GetMessagesForSearchJobRequest(searchJobId, offset, limit);
        return searchJobClient.getMessagesForSearchJob(
                getConnectionConfig(), getMessagesForSearchJobRequest);
    }

    /**
     * Returns records for the specified search job.
     *
     * @param searchJobId The search job ID.
     * @param offset      The offset.
     * @param limit       The length.
     * @return The records.
     */
    @Override
    public GetRecordsForSearchJobResponse getRecordsForSearchJob(String searchJobId, int offset, int limit) {
        GetRecordsForSearchJobRequest getRecordsForSearchJobRequest =
                new GetRecordsForSearchJobRequest(searchJobId, offset, limit);
        return searchJobClient.getRecordsForSearchJob(
                getConnectionConfig(), getRecordsForSearchJobRequest);
    }

    /**
     * Cancels a search job.
     *
     * @param searchJobId The search job ID.
     * @return The response.
     */
    @Override
    public CancelSearchJobResponse cancelSearchJob(String searchJobId) {
        CancelSearchJobRequest cancelSearchJobRequest =
                new CancelSearchJobRequest(searchJobId);
        return searchJobClient.deleteSearchJob(
                getConnectionConfig(), cancelSearchJobRequest);
    }

    //
    // Collectors.
    //

    /**
     * Gets all available Sumo Logic collectors matching the request.
     *
     * @param request The request.
     * @return The response.
     */
    public GetCollectorsResponse getCollectors(GetCollectorsRequest request) {
        return collectorsClient.get(getConnectionConfig(), request);
    }

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The response.
     */
    public GetCollectorsResponse getCollectors() {
        return getCollectors(new GetCollectorsRequest());
    }

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public GetCollectorResponse getCollector(GetCollectorRequest request) {
        return collectorsClient.get(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id.
     * @return The response.
     */
    public GetCollectorResponse getCollector(Long id) {
        return getCollector(new GetCollectorRequest(id));
    }

    /**
     * Creates a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public CreateCollectorResponse createCollector(CreateCollectorRequest request) {
        return collectorsClient.create(getConnectionConfig(), request);
    }

    /**
     * Convenience method; takes a collector as argument.
     *
     * @param collector The collector.
     * @return The response.
     */
    public CreateCollectorResponse createCollector(Collector collector) {
        return createCollector(new CreateCollectorRequest(collector));
    }

    /**
     * Updates a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public UpdateCollectorResponse updateCollector(UpdateCollectorRequest request) {
        return collectorsClient.update(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a collector as argument.
     *
     * @param collector The collector.
     * @return The response.
     */
    public UpdateCollectorResponse updateCollector(Collector collector) {
        return updateCollector(new UpdateCollectorRequest(collector.getId(), collector));
    }

    /**
     * Deletes a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public DeleteCollectorResponse deleteCollector(DeleteCollectorRequest request) {
        return collectorsClient.delete(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id.
     * @return The response.
     */
    public DeleteCollectorResponse deleteCollector(Long id) {
        return deleteCollector(new DeleteCollectorRequest(id));
    }

    /**
     * Gets all sources for a Sumo Logic collector matching the request.
     *
     * @param request The request.
     * @return The response.
     */
    public GetSourcesResponse getSources(GetSourcesRequest request) {
        return collectorsClient.getSources(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes a collector id as argument.
     *
     * @param collectorId The collector id.
     * @return The response.
     */
    public GetSourcesResponse getSources(Long collectorId) {
        return getSources(new GetSourcesRequest(collectorId));
    }

    /**
     * Gets a single source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public GetSourceResponse getSource(GetSourceRequest request) {
        return collectorsClient.getSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id.
     * @param sourceId    The source id.
     * @return The response.
     */
    public GetSourceResponse getSource(Long collectorId, Long sourceId) {
        return getSource(new GetSourceRequest(collectorId, sourceId));
    }

    /**
     * Creates a source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public CreateSourceResponse createSource(CreateSourceRequest request) {
        return collectorsClient.createSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id.
     * @param source      The source.
     * @return The response.
     */
    public CreateSourceResponse createSource(Long collectorId, Source source) {
        return createSource(new CreateSourceRequest(collectorId, source));
    }

    /**
     * Updates a source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public UpdateSourceResponse updateSource(UpdateSourceRequest request) {
        return collectorsClient.updateSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id.
     * @param source      The source.
     * @return The response.
     */
    public UpdateSourceResponse updateSource(Long collectorId, Source source) {
        return updateSource(new UpdateSourceRequest(collectorId, source.getId(), source));
    }

    /**
     * Deletes a source from a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    public DeleteSourceResponse deleteSource(DeleteSourceRequest request) {
        return collectorsClient.deleteSource(getConnectionConfig(), request);
    }

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id.
     * @param sourceId    The source id.
     * @return The response.
     */
    public DeleteSourceResponse deleteSource(Long collectorId, Long sourceId) {
        return deleteSource(new DeleteSourceRequest(collectorId, sourceId));
    }

    //
    // Dashboards.
    //

    /**
     * Returns all dashboards.
     *
     * @param includeMonitors Whether to include the monitors in the response.
     * @return The dashboards.
     */
    public GetDashboardsResponse getDashboards(boolean includeMonitors) {
        return dashboardClient.getDashboards(
                getConnectionConfig(), new GetDashboardsRequest(includeMonitors));
    }

    /**
     * Returns a dashboard.
     *
     * @param id The ID of the dashboard.
     * @return The dashboard.
     */
    public GetDashboardDataResponse getDashboardData(long id) {
        return dashboardClient.getDashboardData(
                getConnectionConfig(), new GetDashboardDataRequest(id));
    }

    /**
     * Returns the data for a dashboard.
     *
     * @param id The ID of the dashboard.
     * @return The data for the dashboard.
     */
    public GetDashboardResponse getDashboard(long id) {
        return dashboardClient.getDashboard(
                getConnectionConfig(), new GetDashboardRequest(id));
    }
}
