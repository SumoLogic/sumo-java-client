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

/**
 * Common url parameters used in the sumo web service API.
 * Contains all magic numbers/strings for performing the HTTP requests.
 */
public interface UrlParameters {

    /**
     * API
     */
    public static String API_SERVICE = "api";

    /**
     * API version prefix
     */
    public static String VERSION_PREFIX = "v";

    /**
     * Logs sub-tasks
     */
    public static String LOGS_SERVICE = "logs";

    /**
     * Collectors sub-tasks
     */
    public static String COLLECTORS_SERVICE = "collectors";

    /**
     * Sources sub-tasks
     */
    public static String SOURCES_SERVICE = "sources";

    /**
     * Limit for pagination
     */
    public static String LIMIT = "limit";

    /**
     * Offset for pagination
     */
    public static String OFFSET = "offset";

    /**
     * Search query string
     */
    public static String SEARCH = "search";

    /**
     * Search suffix for JSON results
     */
    public static String SEARCH_JSON = ".json";

    /**
     * Search suffix for text results
     */
    public static String SEARCH_TEXT = ".text";

    /**
     * The search query string
     */
    public static String SEARCH_QUERY = "q";

    /**
     * The search order parameter string
     */
    public static String SEARCH_ORDER = "order";

    /**
     * Descending search order
     */
    public static String SEARCH_ORDER_DESC = "desc";

    /**
     * Ascending search order
     */
    public static String SEARCH_ORDER_ASC = "asc";

    /**
     * The search from parameter string
     */
    public static String SEARCH_FROM = "from";

    /**
     * The search to parameter string
     */
    public static String SEARCH_TO = "to";

    /**
     * The search timezone parameter string
     */
    public static String SEARCH_TIMEZONE = "tz";

    /**
     * The search pagination offset parameter string
     */
    public static String SEARCH_OFFSET = "offset";

    /**
     * The search pagination limit parameter string
     */
    public static String SEARCH_LIMIT = "limit";

    /**
     * The log message text field's key
     */
    public static String LOG_MESSAGE_RAW = "_raw";

    /**
     * The log message time field's key
     */
    public static String LOG_MESSAGE_TIME = "_messagetime";

    /**
     * The log message receipt time field's key
     */
    public static String LOG_MESSAGE_RECEIPT_TIME = "_receipttime";

    /**
     * The log message receipt source host field's key
     */
    public static String LOG_MESSAGE_SOURCE_HOST = "_sourcehost";

    /**
     * The log message receipt source category field's key
     */
    public static String LOG_MESSAGE_SOURCE_CATEGORY = "_sourcecategory";

    /**
     * The log message receipt source name field's key
     */
    public static String LOG_MESSAGE_SOURCE_NAME = "_sourcename";

    /**
     * The log message count field's key (when results are aggregated)
     */
    public static String LOG_MESSAGE_COUNT = "_count";

    /**
     * The log message signature field's key
     */
    public static String LOG_MESSAGE_SIGNATURE = "_signature";

    /**
     * Search sub-tasks
     */
    public static String SEARCH_JOBS_SERVICE ="search/jobs";

    /**
     * Search job messages
     */
    public static String SEARCH_JOBS_SERVICE_MESSAGES ="messages";

    /**
     * Search job records
     */
    public static String SEARCH_JOBS_SERVICE_RECORDS ="records";

    /**
     * Dashboards sub-tasks
     */
    public static String DASHBOARDS_SERVICE = "dashboards";

    /**
     * Dashboards data
     */
    public static String DASHBOARDS_SERVICE_DATA = "data";
}
