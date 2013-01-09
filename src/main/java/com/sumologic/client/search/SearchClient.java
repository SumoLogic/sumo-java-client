package com.sumologic.client.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sumologic.client.AuthContext;
import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.JacksonUtils;
import com.sumologic.client.util.ResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchClient {

    public SearchResponse search(AuthContext context, SearchRequest request) {
        return HttpUtils.get(context, getSearchEndpoint(), request, new SearchHandler());
    }

    private static String getSearchEndpoint() {
        return UrlParameters.LOGS_SERVICE + "/" + UrlParameters.SEARCH;
    }

    private static class SearchHandler implements ResponseHandler<SearchRequest, SearchResponse> {

        @Override
        public SearchResponse handle(InputStream httpStream,
                                     SearchRequest request) throws IOException {

            List<LogMessage> messages = JacksonUtils.MAPPER.readValue(httpStream,
                    new TypeReference<List<LogMessage>>() {});
            return new SearchResponse(request, messages);
        }
    }
}
