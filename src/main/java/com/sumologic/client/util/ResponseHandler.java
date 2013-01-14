package com.sumologic.client.util;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public interface ResponseHandler<Request, Response> {

    public Response handle(HttpResponse httpResponse, InputStream httpStream, Request request)
            throws IOException;
}
