package com.sumologic.client.searchsession;

import org.apache.http.HttpStatus;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.searchsession.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchSessionClient {

    // Constants.

    private static final String CREATE_SEARCH_SESSION_ENDPOINT =
            UrlParameters.SEARCH_SERVICE +
                    "/" + UrlParameters.SEARCH_SESSIONS_SERVICE;
    private static final String GET_SEARCH_SESSION_STATUS_ENDPOINT =
            UrlParameters.SEARCH_SERVICE +
                    "/" + UrlParameters.SEARCH_SESSIONS_SERVICE;
    private static final String GET_MESSAGES_FOR_SEARCH_SESSION_ENDPOINT =
            UrlParameters.SEARCH_SERVICE +
                    "/" + UrlParameters.MESSAGES_SEARCH_SESSIONS_SERVICE;
    private static final String GET_RECORDS_FOR_SEARCH_SESSION_ENDPOINT =
            UrlParameters.SEARCH_SERVICE +
                    "/" + UrlParameters.RECORDS_SEARCH_SESSIONS_SERVICE;
    private static final String CANCEL_SEARCH_SESSION_ENDPOINT =
            UrlParameters.SEARCH_SERVICE +
                    "/" + UrlParameters.SEARCH_SESSIONS_SERVICE;

    // Implementation.

    public String createSearchSession(
            ConnectionConfig connection,
            CreateSearchSessionRequest createSearchSessionRequest) {

        String uri = CREATE_SEARCH_SESSION_ENDPOINT;
        return HttpUtils.post(
                connection,
                uri,
                createSearchSessionRequest,
                HttpUtils.toRequestHeaders(
                        "Content-type", "application/json",
                        "Accept", "application/json"),
                new DeserializingResponseHandler<CreateSearchSessionRequest,
                        CreateSearchSessionResponse>(CreateSearchSessionResponse.class),
                HttpStatus.SC_ACCEPTED).getId();
    }

    public GetSearchSessionStatusResponse getSearchSessionStatus(
            ConnectionConfig connection,
            GetSearchSessionStatusRequest getSearchSessionStatusRequest) {

        String uri = GET_SEARCH_SESSION_STATUS_ENDPOINT +
                "/" + getSearchSessionStatusRequest.getId();
        return HttpUtils.get(
                connection,
                uri,
                getSearchSessionStatusRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetSearchSessionStatusRequest,
                        GetSearchSessionStatusResponse>(GetSearchSessionStatusResponse.class),
                HttpStatus.SC_OK);
    }

    public GetMessagesForSearchSessionResponse getMessagesForSearchSession(
            ConnectionConfig connection,
            GetMessagesForSearchSessionRequest getMessagesForSearchSessionRequest) {

        String uri = GET_MESSAGES_FOR_SEARCH_SESSION_ENDPOINT +
                "/" + getMessagesForSearchSessionRequest.getId();
        return HttpUtils.get(
                connection,
                uri,
                getMessagesForSearchSessionRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetMessagesForSearchSessionRequest,
                        GetMessagesForSearchSessionResponse>(GetMessagesForSearchSessionResponse.class),
                HttpStatus.SC_OK);
    }

    public GetRecordsForSearchSessionResponse getRecordsForSearchSession(
            ConnectionConfig connection,
            GetRecordsForSearchSessionRequest getRecordsForSearchSessionRequest) {

        String uri = GET_RECORDS_FOR_SEARCH_SESSION_ENDPOINT +
                "/" + getRecordsForSearchSessionRequest.getId();
        return HttpUtils.get(
                connection,
                uri,
                getRecordsForSearchSessionRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetRecordsForSearchSessionRequest,
                        GetRecordsForSearchSessionResponse>(GetRecordsForSearchSessionResponse.class),
                HttpStatus.SC_OK);
    }

    public CancelSearchSessionResponse deleteSearchSession(
            ConnectionConfig connection,
            CancelSearchSessionRequest cancelSearchSessionRequest) {

        String uri = CANCEL_SEARCH_SESSION_ENDPOINT +
                "/" + cancelSearchSessionRequest.getId();
        return HttpUtils.delete(
                connection,
                uri,
                cancelSearchSessionRequest,
                new DeserializingResponseHandler<CancelSearchSessionRequest,
                        CancelSearchSessionResponse>(CancelSearchSessionResponse.class),
                HttpStatus.SC_OK);
    }
}
