package com.sumologic.client.collectors;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import com.sumologic.client.util.PassingResponseHandler;
import org.apache.http.HttpStatus;
import com.sumologic.client.util.SumoEntityResponseHandler;

public class CollectorsClient {

    public GetCollectorsResponse get(ConnectionConfig config, GetCollectorsRequest request) {
        return HttpUtils.get(config, UrlParameters.COLLECTORS_SERVICE, request,
                HttpUtils.toRequestHeaders(),
                new DeserializingResponseHandler<GetCollectorsRequest,
                        GetCollectorsResponse>(GetCollectorsResponse.class),
                HttpStatus.SC_OK);
    }

    public GetCollectorResponse get(ConnectionConfig config, GetCollectorRequest request) {
        return HttpUtils.get(config, getCollectorEndpoint(request.getId()), request,
                HttpUtils.toRequestHeaders(),
                new SumoEntityResponseHandler<GetCollectorRequest,
                        GetCollectorResponse>(GetCollectorResponse.class),
                HttpStatus.SC_OK);
    }

    public UpdateCollectorResponse update(ConnectionConfig config, UpdateCollectorRequest request) {
        return HttpUtils.put(config, getCollectorEndpoint(request.getId()), request,
                new SumoEntityResponseHandler<UpdateCollectorRequest,
                        UpdateCollectorResponse>(UpdateCollectorResponse.class),
                HttpStatus.SC_OK);
    }

    public DeleteCollectorResponse delete(ConnectionConfig config, DeleteCollectorRequest request) {
        return HttpUtils.delete(config, getCollectorEndpoint(request.getId()), request,
                new PassingResponseHandler<DeleteCollectorRequest,
                        DeleteCollectorResponse>(new DeleteCollectorResponse()),
                HttpStatus.SC_OK);
    }

    public GetSourcesResponse getSources(ConnectionConfig config, GetSourcesRequest request) {
        return HttpUtils.get(config, getSourcesEndpoint(request.getCollectorId()), request,
                HttpUtils.toRequestHeaders(),
                new DeserializingResponseHandler<GetSourcesRequest,
                        GetSourcesResponse>(GetSourcesResponse.class),
                HttpStatus.SC_OK);
    }

    public GetSourceResponse getSource(ConnectionConfig config, GetSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.get(config, sourceEndpoint, request,
                HttpUtils.toRequestHeaders(),
                new SumoEntityResponseHandler<GetSourceRequest,
                        GetSourceResponse>(GetSourceResponse.class),
                HttpStatus.SC_OK);
    }

    public CreateSourceResponse createSource(ConnectionConfig config, CreateSourceRequest request) {
        return HttpUtils.post(config, getSourcesEndpoint(request.getCollectorId()), request,
                HttpUtils.toRequestHeaders(),
                new SumoEntityResponseHandler<CreateSourceRequest,
                        CreateSourceResponse>(CreateSourceResponse.class),
                HttpStatus.SC_OK);
    }

    public UpdateSourceResponse updateSource(ConnectionConfig config, UpdateSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.put(config, sourceEndpoint, request,
                new SumoEntityResponseHandler<UpdateSourceRequest,
                        UpdateSourceResponse>(UpdateSourceResponse.class),
                HttpStatus.SC_OK);
    }

    public DeleteSourceResponse deleteSource(ConnectionConfig config, DeleteSourceRequest request) {
        String sourceEndpoint = getSourceEndpoint(request.getCollectorId(), request.getSourceId());
        return HttpUtils.delete(config, sourceEndpoint, request,
                new PassingResponseHandler<DeleteSourceRequest,
                        DeleteSourceResponse>(new DeleteSourceResponse()),
                HttpStatus.SC_OK);
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
