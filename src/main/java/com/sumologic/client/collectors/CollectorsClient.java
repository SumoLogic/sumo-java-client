package com.sumologic.client.collectors;

import com.sumologic.client.AuthContext;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.PassingResponseHandler;

public class CollectorsClient {

    public GetCollectorsResponse get(AuthContext context, GetCollectorsRequest request) {
        return HttpUtils.get(context, UrlParameters.COLLECTORS_SERVICE, request,
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class));
    }

    public GetCollectorResponse get(AuthContext context, GetCollectorRequest request) {
        return HttpUtils.get(context, getCollectorEndpoint(request.getId()), request,
                new DeserializingResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class));
    }

    public UpdateCollectorResponse update(AuthContext context, UpdateCollectorRequest request) {
        return HttpUtils.put(context, getCollectorEndpoint(request.getId()), request,
                new DeserializingResponseHandler<UpdateCollectorRequest,
                        UpdateCollectorResponse>(UpdateCollectorResponse.class));
    }

    public DeleteCollectorResponse delete(AuthContext context, DeleteCollectorRequest request) {
        return HttpUtils.delete(context, getCollectorEndpoint(request.getId()), request,
                new PassingResponseHandler<DeleteCollectorRequest,
                        DeleteCollectorResponse>(new DeleteCollectorResponse()));
    }

    public GetSourcesResponse getSources(AuthContext context, GetSourcesRequest request) {
        return HttpUtils.get(context, getSourcesEndpoint(request.getCollectorId()), request,
                new DeserializingResponseHandler<GetSourcesRequest,
                        GetSourcesResponse>(GetSourcesResponse.class));
    }

    public GetSourceResponse getSource(AuthContext context, GetSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.get(context, sourceEndpoint, request,
                new DeserializingResponseHandler<GetSourceRequest,
                        GetSourceResponse>(GetSourceResponse.class));
    }

    public CreateSourceResponse createSource(AuthContext context, CreateSourceRequest request) {
        return HttpUtils.post(context, getSourcesEndpoint(request.getCollectorId()), request,
                new DeserializingResponseHandler<CreateSourceRequest,
                        CreateSourceResponse>(CreateSourceResponse.class));
    }

    public UpdateSourceResponse updateSource(AuthContext context, UpdateSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.put(context, sourceEndpoint, request,
                new DeserializingResponseHandler<UpdateSourceRequest,
                        UpdateSourceResponse>(UpdateSourceResponse.class));
    }

    public DeleteSourceResponse deleteSource(AuthContext context, DeleteSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.delete(context, sourceEndpoint, request,
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
