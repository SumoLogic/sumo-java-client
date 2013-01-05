package com.sumologic.client.collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.model.GetCollectorsRequest;
import com.sumologic.client.model.GetCollectorsResponse;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.HttpUtils.ResponseHandler;

import java.io.IOException;
import java.io.InputStream;

public class CollectorsClient {

    public static GetCollectorsResponse get(String protocol, String hostname, int port,
                                         Credentials credentials, GetCollectorsRequest request) {
        return HttpUtils.httpGet(protocol, hostname, port, credentials,
                getCollectorsEndpoint(request), request, new CollectorsHandler());
    }

    private static String getCollectorsEndpoint(GetCollectorsRequest request) {
        return UrlParameters.COLLECTORS_SERVICE +
                (request.getId() == null ? "" : "/" + request.getId());
    }

    private static class CollectorsHandler implements ResponseHandler<GetCollectorsRequest, GetCollectorsResponse> {

        @Override
        public GetCollectorsResponse handle(InputStream httpStream,
                                            GetCollectorsRequest request) throws IOException {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(httpStream, GetCollectorsResponse.class);
        }
    }
}
