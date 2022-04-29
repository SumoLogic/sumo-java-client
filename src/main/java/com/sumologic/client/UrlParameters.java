/*
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

/**
 * Common url parameters used in the sumo web service API.
 * Contains all magic numbers/strings for performing the HTTP requests.
 */
public interface UrlParameters {

    /**
     * API
     */
    String API_SERVICE = "api";

    /**
     * API version prefix
     */
    String VERSION_PREFIX = "v";

    /**
     * Logs sub-tasks
     */
    String LOGS_SERVICE = "logs";

    /**
     * Collectors sub-tasks
     */
    String COLLECTORS_SERVICE = "collectors";

    /**
     * Sources sub-tasks
     */
    String SOURCES_SERVICE = "sources";

    /**
     * Limit for pagination
     */
    String LIMIT = "limit";

    /**
     * Offset for pagination
     */
    String OFFSET = "offset";

    /**
     * Search query string
     */
    String SEARCH = "search";

    /**
     * Search suffix for JSON results
     */
    String SEARCH_JSON = ".json";

    /**
     * Search suffix for text results
     */
    String SEARCH_TEXT = ".text";

    /**
     * The search query string
     */
    String SEARCH_QUERY = "q";

    /**
     * The search order parameter string
     */
    String SEARCH_ORDER = "order";

    /**
     * Descending search order
     */
    String SEARCH_ORDER_DESC = "desc";

    /**
     * Ascending search order
     */
    String SEARCH_ORDER_ASC = "asc";

    /**
     * The search from parameter string
     */
    String SEARCH_FROM = "from";

    /**
     * The search to parameter string
     */
    String SEARCH_TO = "to";

    /**
     * The search timezone parameter string
     */
    String SEARCH_TIMEZONE = "tz";

    /**
     * The search pagination offset parameter string
     */
    String SEARCH_OFFSET = "offset";

    /**
     * The search pagination limit parameter string
     */
    String SEARCH_LIMIT = "limit";

    /**
     * The log message text field's key
     */
    String LOG_MESSAGE_RAW = "_raw";

    /**
     * The log message time field's key
     */
    String LOG_MESSAGE_TIME = "_messagetime";

    /**
     * The log message receipt time field's key
     */
    String LOG_MESSAGE_RECEIPT_TIME = "_receipttime";

    /**
     * The log message receipt source host field's key
     */
    String LOG_MESSAGE_SOURCE_HOST = "_sourcehost";

    /**
     * The log message receipt source category field's key
     */
    String LOG_MESSAGE_SOURCE_CATEGORY = "_sourcecategory";

    /**
     * The log message receipt source name field's key
     */
    String LOG_MESSAGE_SOURCE_NAME = "_sourcename";

    /**
     * The log message count field's key (when results are aggregated)
     */
    String LOG_MESSAGE_COUNT = "_count";

    /**
     * The log message signature field's key
     */
    String LOG_MESSAGE_SIGNATURE = "_signature";

    /**
     * Search sub-tasks
     */
    String SEARCH_JOBS_SERVICE = "search/jobs";

    /**
     * Search job messages
     */
    String SEARCH_JOBS_SERVICE_MESSAGES = "messages";

    /**
     * Search job records
     */
    String SEARCH_JOBS_SERVICE_RECORDS = "records";

    /**
     * Dashboards sub-tasks
     */
    String DASHBOARDS_SERVICE = "dashboards";

    /**
     * Dashboards data
     */
    String DASHBOARDS_SERVICE_DATA = "data";
}
