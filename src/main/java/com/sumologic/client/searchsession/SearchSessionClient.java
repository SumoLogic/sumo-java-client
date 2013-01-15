package com.sumologic.client.searchsession;

import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.searchsession.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.PassingResponseHandler;
import org.apache.http.HttpStatus;

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
    
    // Implementation.

    public String createSearchSession(
            String protocol, String hostname, int port, Credentials credentials,
            CreateSearchSessionRequest createSearchSessionRequest) {

        return HttpUtils.post(
                protocol, hostname, port, credentials,
                CREATE_SEARCH_SESSION_ENDPOINT,
                createSearchSessionRequest,
                HttpUtils.toRequestHeaders(
                        "Content-type", "application/json",
                        "Accept", "application/json"),
                new DeserializingResponseHandler<CreateSearchSessionRequest,
                        CreateSearchSessionResponse>(CreateSearchSessionResponse.class),
                HttpStatus.SC_CREATED).getId();
    }

    public GetSearchSessionStatusResponse getSearchSessionStatus(
            String protocol, String hostname, int port, Credentials credentials,
            GetSearchSessionStatusRequest getSearchSessionStatusRequest) {

        return HttpUtils.get(
                protocol, hostname, port, credentials,
                GET_SEARCH_SESSION_STATUS_ENDPOINT + "/" + getSearchSessionStatusRequest.getId(),
                getSearchSessionStatusRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetSearchSessionStatusRequest,
                        GetSearchSessionStatusResponse>(GetSearchSessionStatusResponse.class),
                HttpStatus.SC_OK);
    }

    public GetMessagesForSearchSessionResponse getMessagesForSearchSession(
            String protocol, String hostname, int port, Credentials credentials,
            GetMessagesForSearchSessionRequest getMessagesForSearchSessionRequest) {

        return HttpUtils.get(
                protocol, hostname, port, credentials,
                GET_MESSAGES_FOR_SEARCH_SESSION_ENDPOINT +
                        "/" + getMessagesForSearchSessionRequest.getId(),
                getMessagesForSearchSessionRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetMessagesForSearchSessionRequest,
                        GetMessagesForSearchSessionResponse>(GetMessagesForSearchSessionResponse.class),
                HttpStatus.SC_OK);
    }
}
