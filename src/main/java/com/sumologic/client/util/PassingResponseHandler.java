package com.sumologic.client.util;

import java.io.IOException;
import java.io.InputStream;

public class PassingResponseHandler<Request, Response>
        implements ResponseHandler<Request, Response> {

    private Response response;

    public PassingResponseHandler(Response response) {
        this.response = response;
    }

    @Override
    public Response handle(InputStream httpStream, Request request) throws IOException {
        return response;
    }
}
