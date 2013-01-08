package com.sumologic.client.collectors;

import com.sumologic.client.Credentials;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.DeserializingResponseHandler;

public class CollectorsClient {

    public static GetCollectorsResponse
    get(String protocol, String hostname, int port,
        Credentials credentials, GetCollectorsRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/", request,
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class));
    }

    public static GetCollectorResponse
    get(String protocol, String hostname, int port,
        Credentials credentials, GetCollectorRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class));
    }

    public static ModifyCollectorResponse
    modify(String protocol, String hostname, int port,
           Credentials credentials, ModifyCollectorRequest request) {

        return HttpUtils.put(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<ModifyCollectorRequest,
                        ModifyCollectorResponse>(ModifyCollectorResponse.class));
    }

    public static DeleteCollectorResponse
    delete(String protocol, String hostname, int port,
           Credentials credentials, DeleteCollectorRequest request) {

        return HttpUtils.delete(protocol, hostname, port, credentials,
                UrlParameters.COLLECTORS_SERVICE + "/" + request.getId(), request,
                new DeserializingResponseHandler<DeleteCollectorRequest,
                        DeleteCollectorResponse>(DeleteCollectorResponse.class));
    }
}
