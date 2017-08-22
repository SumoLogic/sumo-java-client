/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.dashboard.DashboardClient;
import com.sumologic.client.dashboard.model.*;
import com.sumologic.client.searchjob.SearchJobClient;
import com.sumologic.client.searchjob.model.*;
import com.sumologic.client.util.HttpUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Sumo Logic API client implementation.
 */
public class SumoLogicClient implements SumoLogic {

    private HttpUtils httpUtils = new HttpUtils();

    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private int port = 443;
    private Credentials credentials;
    private String proxyHost;
    private int proxyPort;
    private String proxyProtocol;

    private CollectorsClient collectorsClient = new CollectorsClient(httpUtils);
    private SearchJobClient searchJobClient = new SearchJobClient(httpUtils);
    private DashboardClient dashboardClient = new DashboardClient(httpUtils);

    /**
     * Constructs a Sumo Logic client.
     *
     * @param credentials The credential used to access sumo logic's web service.
     */
    public SumoLogicClient(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Convenience: constructs the credentials using accessId and accessKey.
     *
     * @param accessId    Your access id.
     * @param accessKey Your access key.
     */
    public SumoLogicClient(String accessId, String accessKey) {
        this.credentials = new Credentials(accessId, accessKey);
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

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setProxyProtocol(String proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
    }

    private ConnectionConfig getConnectionConfig() {
        return new ConnectionConfig(protocol, hostname, port, credentials, proxyProtocol, proxyHost, proxyPort);
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
