package com.sumologic.client.util;

import java.io.IOException;
import java.io.InputStream;

public class DeserializingResponseHandler<Request, Response>
        implements ResponseHandler<Request, Response> {

    private Class<Response> clazz;

    public DeserializingResponseHandler(Class<Response> clazz) {
        this.clazz = clazz;
    }

    public Response handle(InputStream httpStream, Request request) throws IOException {
        return JacksonUtils.MAPPER.readValue(httpStream, clazz);
    }
}
