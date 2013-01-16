package com.sumologic.client;

/**
 * Common url parameters used in the sumo web service API.
 * Contains all magic numbers/strings for performing the HTTP requests.
 *
 * @author Sebastian Mies
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
    public static String LOG_MESSAGE_SOURCE_NAME = "_blade";

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
    public static String SEARCH_SERVICE ="search";

    /**
     * Search sessions sub-tasks
     */
    public static String SEARCH_SESSIONS_SERVICE ="sessions";

    /**
     * Search session messages
     */
    public static String MESSAGES_SEARCH_SESSIONS_SERVICE ="messages";

    /**
     * Search session records
     */
    public static String RECORDS_SEARCH_SESSIONS_SERVICE ="records";
}
