package com.sumologic.client.util;

import java.io.IOException;
import java.io.InputStream;

public interface ResponseHandler<Request, Response> {

    public Response handle(InputStream httpStream, Request request) throws IOException;
}
