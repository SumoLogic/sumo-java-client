package com.sumologic.client.util;

import com.sumologic.client.model.SumoEntityResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class SumoEntityResponseHandler<Request, Response extends SumoEntityResponse>
        implements ResponseHandler<Request, Response> {

    private Class<Response> clazz;

    public SumoEntityResponseHandler(Class<Response> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Response handle(HttpResponse httpResponse, InputStream httpStream, Request request)
            throws IOException {

        Response response = JacksonUtils.MAPPER.readValue(httpStream, clazz);
        response.setETag(httpResponse.getFirstHeader("ETag").getValue());
        return response;
    }
}
