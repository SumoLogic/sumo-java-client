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

    public GetSourcesResponse getSources(String protocol, String hostname, int port,
                                         Credentials credentials, GetSourcesRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                getSourcesUrl(request.getCollectorId()), request,
                new DeserializingResponseHandler<GetSourcesRequest,
                        GetSourcesResponse>(GetSourcesResponse.class));
    }

    public GetSourceResponse getSource(String protocol, String hostname, int port,
                                       Credentials credentials, GetSourceRequest request) {

        return HttpUtils.get(protocol, hostname, port, credentials,
                getSourcesUrl(request.getCollectorId()) + "/" + request.getSourceId(), request,
                new DeserializingResponseHandler<GetSourceRequest,
                        GetSourceResponse>(GetSourceResponse.class));
    }

    public CreateSourceResponse createSource(String protocol, String hostname, int port,
                                             Credentials credentials, CreateSourceRequest request) {
        return HttpUtils.post(protocol, hostname, port, credentials,
                getSourcesUrl(request.getCollectorId()), request,
                new DeserializingResponseHandler<CreateSourceRequest,
                        CreateSourceResponse>(CreateSourceResponse.class));
    }

    public UpdateSourceResponse updateSource(String protocol, String hostname, int port,
                                             Credentials credentials, UpdateSourceRequest request) {

        return HttpUtils.put(protocol, hostname, port, credentials,
                getSourcesUrl(request.getCollectorId()) + "/" + request.getSourceId(), request,
                new DeserializingResponseHandler<UpdateSourceRequest,
                        UpdateSourceResponse>(UpdateSourceResponse.class));
    }

    public DeleteSourceResponse deleteSource(String protocol, String hostname, int port,
                                             Credentials credentials, DeleteSourceRequest request) {

        return HttpUtils.delete(protocol, hostname, port, credentials,
                getSourcesUrl(request.getCollectorId()) + "/" + request.getSourceId(), request,
                new PassingResponseHandler<DeleteSourceRequest,
                        DeleteSourceResponse>(new DeleteSourceResponse()));
    }

    private String getSourcesUrl(Long collectorId) {
        return UrlParameters.COLLECTORS_SERVICE +
                "/" + collectorId +
                "/" + UrlParameters.SOURCES_SERVICE;
    }
}
