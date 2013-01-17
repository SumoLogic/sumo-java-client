package com.sumologic.client.util;

import com.sumologic.client.model.SumoEntityResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class SumoEntityResponseHandler<Request, Response extends SumoEntityResponse>
        implements ResponseHandler<Request, Response> {

    private static String ETAG_HEADER = "ETag";

    private Class<Response> clazz;

    public SumoEntityResponseHandler(Class<Response> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Response handle(HttpResponse httpResponse, InputStream httpStream, Request request)
            throws IOException {

        Response response = JacksonUtils.MAPPER.readValue(httpStream, clazz);
        if (httpResponse.containsHeader(ETAG_HEADER)) {
            response.setETag(httpResponse.getFirstHeader(ETAG_HEADER).getValue());
        }
        return response;
    }
}
