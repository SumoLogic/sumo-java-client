package com.sumologic.client.collectors;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.PassingResponseHandler;
import com.sumologic.client.util.SumoEntityResponseHandler;

public class CollectorsClient {

    public GetCollectorsResponse get(ConnectionConfig config, GetCollectorsRequest request) {
        return HttpUtils.get(config, UrlParameters.COLLECTORS_SERVICE, request,
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class));
    }

    public GetCollectorResponse get(ConnectionConfig config, GetCollectorRequest request) {
        return HttpUtils.get(config, getCollectorEndpoint(request.getId()), request,
                new SumoEntityResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class));
    }

    public UpdateCollectorResponse update(ConnectionConfig config, UpdateCollectorRequest request) {
        return HttpUtils.put(config, getCollectorEndpoint(request.getId()), request,
                new DeserializingResponseHandler<UpdateCollectorRequest,
                        UpdateCollectorResponse>(UpdateCollectorResponse.class));
    }

    public DeleteCollectorResponse delete(ConnectionConfig config, DeleteCollectorRequest request) {
        return HttpUtils.delete(config, getCollectorEndpoint(request.getId()), request,
                new PassingResponseHandler<DeleteCollectorRequest,
                        DeleteCollectorResponse>(new DeleteCollectorResponse()));
    }

    public GetSourcesResponse getSources(ConnectionConfig config, GetSourcesRequest request) {
        return HttpUtils.get(config, getSourcesEndpoint(request.getCollectorId()), request,
                new DeserializingResponseHandler<GetSourcesRequest,
                        GetSourcesResponse>(GetSourcesResponse.class));
    }

    public GetSourceResponse getSource(ConnectionConfig config, GetSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.get(config, sourceEndpoint, request,
                new SumoEntityResponseHandler<GetSourceRequest,
                        GetSourceResponse>(GetSourceResponse.class));
    }

    public CreateSourceResponse createSource(ConnectionConfig config, CreateSourceRequest request) {
        return HttpUtils.post(config, getSourcesEndpoint(request.getCollectorId()), request,
                new DeserializingResponseHandler<CreateSourceRequest,
                        CreateSourceResponse>(CreateSourceResponse.class));
    }

    public UpdateSourceResponse updateSource(ConnectionConfig config, UpdateSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.put(config, sourceEndpoint, request,
                new DeserializingResponseHandler<UpdateSourceRequest,
                        UpdateSourceResponse>(UpdateSourceResponse.class));
    }

    public DeleteSourceResponse deleteSource(ConnectionConfig config, DeleteSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.delete(config, sourceEndpoint, request,
                new PassingResponseHandler<DeleteSourceRequest,
                        DeleteSourceResponse>(new DeleteSourceResponse()));
    }

    private String getCollectorEndpoint(Long collectorId) {
        return UrlParameters.COLLECTORS_SERVICE + "/" + collectorId;
    }

    private String getSourcesEndpoint(Long collectorId) {
        return getCollectorEndpoint(collectorId) + "/" + UrlParameters.SOURCES_SERVICE;
    }

    private String getSourceEndpoint(Long collectorId, Long sourceId) {
        return getSourcesEndpoint(collectorId) + "/" + sourceId;
    }
}
