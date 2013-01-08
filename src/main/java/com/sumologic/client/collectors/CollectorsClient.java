package com.sumologic.client.collectors;

import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.GetCollectorRequest;
import com.sumologic.client.collectors.model.GetCollectorResponse;
import com.sumologic.client.collectors.model.GetCollectorsRequest;
import com.sumologic.client.collectors.model.GetCollectorsResponse;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.DeserializingResponseHandler;

public class CollectorsClient {

    public static GetCollectorsResponse get(String protocol, String hostname, int port,
                                            Credentials credentials, GetCollectorsRequest request) {
        return HttpUtils.httpGet(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/", request,
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class));
    }

    public static GetCollectorResponse get(String protocol, String hostname, int port,
                                           Credentials credentials, GetCollectorRequest request) {
        return HttpUtils.httpGet(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class));
    }
}
