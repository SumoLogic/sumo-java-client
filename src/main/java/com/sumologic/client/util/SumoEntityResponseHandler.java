/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client.util;

import com.sumologic.client.model.SumoEntityResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class SumoEntityResponseHandler<Request, Response extends SumoEntityResponse>
        implements ResponseHandler<Request, Response> {

    private static final String ETAG_HEADER = "ETag";

    private final Class<Response> clazz;

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
