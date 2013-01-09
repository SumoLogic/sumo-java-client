package com.sumologic.client.collectors;

import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.PassingResponseHandler;

public class CollectorsClient {

    public GetCollectorsResponse get(String protocol, String hostname, int port,
                                     Credentials credentials, GetCollectorsRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE, request,
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class));
    }

    public GetCollectorResponse get(String protocol, String hostname, int port,
                                    Credentials credentials, GetCollectorRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class));
    }

    public UpdateCollectorResponse update(String protocol, String hostname, int port,
                                          Credentials credentials, UpdateCollectorRequest request) {

        return HttpUtils.put(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<UpdateCollectorRequest,
                        UpdateCollectorResponse>(UpdateCollectorResponse.class));
    }

    public DeleteCollectorResponse delete(String protocol, String hostname, int port,
                                          Credentials credentials, DeleteCollectorRequest request) {

        return HttpUtils.delete(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new PassingResponseHandler<DeleteCollectorRequest,
                        DeleteCollectorResponse>(new DeleteCollectorResponse()));
    }
}
