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

import com.sumologic.client.collectors.model.*;
import com.sumologic.client.searchjob.model.CancelSearchJobResponse;
import com.sumologic.client.searchjob.model.GetMessagesForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetRecordsForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetSearchJobStatusResponse;

/**
 * Provides an interface for accessing Sumo Logic's log web service.
 */
public interface SumoLogic {

    //
    // Search jobs.
    //

    /**
     * Starts a search job and receive a job ID for subsequent
     * polling of the search status.
     *
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     * @return The search job ID.
     */
    String createSearchJob(
            String query, String fromExpression, String toExpression, String timeZone);

    /**
     * Returns the current status of a search job.
     *
     * @param searchJobId The search job ID.
     * @return The status.
     */
    GetSearchJobStatusResponse getSearchJobStatus(String searchJobId);

    /**
     * Returns messages for the specified search job.
     *
     * @param searchJobId The search job ID.
     * @param offset      The offset.
     * @param limit       The length.
     * @return The messages.
     */
    GetMessagesForSearchJobResponse getMessagesForSearchJob(
            String searchJobId, int offset, int limit);

    /**
     * Returns records for the specified search job.
     *
     * @param searchJobId The search job ID.
     * @param offset      The offset.
     * @param limit       The length.
     * @return The records.
     */
    GetRecordsForSearchJobResponse getRecordsForSearchJob(
            String searchJobId, int offset, int limit);

    /**
     * Cancels a search job.
     *
     * @param searchJobId The search job ID.
     * @return The response.
     */
    CancelSearchJobResponse cancelSearchJob(String searchJobId);

    //
    // Collectors.
    //

    /**
     * Gets all available Sumo Logic collectors matching the request.
     *
     * @param request The request.
     * @return The response.
     */
    GetCollectorsResponse getCollectors(GetCollectorsRequest request);

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The response.
     */
    GetCollectorsResponse getCollectors();

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    GetCollectorResponse getCollector(GetCollectorRequest request);

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id.
     * @return The response.
     */
    GetCollectorResponse getCollector(Long id);

    /**
     * Creates a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    CreateCollectorResponse createCollector(CreateCollectorRequest request);

    /**
     * Convenience method; takes a collector as argument.
     *
     * @param collector The collector.
     * @return The response.
     */
    CreateCollectorResponse createCollector(Collector collector);

    /**
     * Updates a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    UpdateCollectorResponse updateCollector(UpdateCollectorRequest request);

    /**
     * Convenience method: takes a collector as argument.
     *
     * @param collector The collector.
     * @return The response.
     */
    UpdateCollectorResponse updateCollector(Collector collector);

    /**
     * Deletes a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    DeleteCollectorResponse deleteCollector(DeleteCollectorRequest request);

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id.
     * @return The response.
     */
    DeleteCollectorResponse deleteCollector(Long id);

    /**
     * Gets all sources for a Sumo Logic collector matching the request.
     *
     * @param request The request.
     * @return The response.
     */
    GetSourcesResponse getSources(GetSourcesRequest request);

    /**
     * Convenience method: takes a collector id as argument.
     *
     * @param collectorId The collector id.
     * @return The response.
     */
    GetSourcesResponse getSources(Long collectorId);

    /**
     * Gets a single source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    GetSourceResponse getSource(GetSourceRequest request);

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id.
     * @param sourceId    The source id.
     * @return The response.
     */
    GetSourceResponse getSource(Long collectorId, Long sourceId);

    /**
     * Creates a source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    CreateSourceResponse createSource(CreateSourceRequest request);

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id.
     * @param source      The source.
     * @return The response.
     */
    CreateSourceResponse createSource(Long collectorId, Source source);

    /**
     * Updates a source for a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    UpdateSourceResponse updateSource(UpdateSourceRequest request);

    /**
     * Convenience method: takes collector id and source as arguments.
     *
     * @param collectorId The collector id.
     * @param source      The source.
     * @return The response.
     */
    UpdateSourceResponse updateSource(Long collectorId, Source source);

    /**
     * Deletes a source from a Sumo Logic collector.
     *
     * @param request The request.
     * @return The response.
     */
    DeleteSourceResponse deleteSource(DeleteSourceRequest request);

    /**
     * Convenience method: takes collector id and source id as arguments.
     *
     * @param collectorId The collector id.
     * @param sourceId    The source id.
     * @return The response.
     */
    DeleteSourceResponse deleteSource(Long collectorId, Long sourceId);

    //
    // Metrics jobs.
    //

    /**
     * Starts a metrics search job and receive a job ID for subsequent
     * polling of the search status.
     *
     * @param query          The query.
     * @param fromExpression The from expression.
     * @param toExpression   The toExpression.
     * @param timeZone       The time zone.
     * @return The search job ID.
     */
    String createMetricsJob(
            String query, String fromExpression, String toExpression, String timeZone);

}
