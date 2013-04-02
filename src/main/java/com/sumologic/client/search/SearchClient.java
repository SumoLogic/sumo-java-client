package com.sumologic.client.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.Defaults;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.JacksonUtils;
import com.sumologic.client.util.ResponseHandler;
import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SearchClient {

    private HttpUtils httpUtils;

    public SearchClient(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public SearchResponse search(ConnectionConfig config, SearchRequest request) {
      return search(config, request, Defaults.DEFAULT_HTTP_SEARCH_TIMEOUT);
    }

    public SearchResponse search(ConnectionConfig config, SearchRequest request, int timeout) {
        return httpUtils.get(config, getSearchEndpoint(), request, timeout,
                HttpUtils.toRequestHeaders(), new SearchHandler(), HttpStatus.SC_OK);
    }

    private static String getSearchEndpoint() {
        return UrlParameters.LOGS_SERVICE + "/" + UrlParameters.SEARCH;
    }

    private static class SearchHandler implements ResponseHandler<SearchRequest, SearchResponse> {

        @Override
        public SearchResponse handle(HttpResponse response, InputStream httpStream,
                                     SearchRequest request) throws IOException {

            List<LogMessage> messages = JacksonUtils.MAPPER.readValue(httpStream,
                    new TypeReference<List<LogMessage>>() {
                    });
            return new SearchResponse(request, messages);
        }
    }
}
