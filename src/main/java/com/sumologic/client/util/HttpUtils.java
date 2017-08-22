/**
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sumologic.client.*;
import com.sumologic.client.model.HttpDeleteRequest;
import com.sumologic.client.model.HttpGetRequest;
import com.sumologic.client.model.HttpPostRequest;
import com.sumologic.client.model.HttpPutRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static final int API_VERSION = 1;

    private static final String JSON_CONTENT_TYPE = "application/json";

    private final CookieStore cookieStore = new BasicCookieStore();

    private final AuthCache authCache = new BasicAuthCache();

    // Public HTTP request methods

    public <Request extends HttpGetRequest, Response> Response
    get(ConnectionConfig config, String endpoint,
        Request request, Map<String, String> requestHeaders,
        ResponseHandler<Request, Response> handler, int expectedStatusCode) {
        return get(config, endpoint, request, Defaults.DEFAULT_HTTP_TIMEOUT, requestHeaders, handler, expectedStatusCode);
    }

    public <Request extends HttpGetRequest, Response> Response
    get(ConnectionConfig config, String endpoint,
        Request request, int timeout, Map<String, String> requestHeaders,
        ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            URI uri = new URIBuilder()
                    .setScheme(config.getProtocol())
                    .setHost(config.getHostname())
                    .setPort(config.getPort())
                    .setPath(getEndpointURI(endpoint))
                    .setParameters(request.toUrlParams())
                    .setCharset(StandardCharsets.UTF_8)
                    .build();
            HttpGet get = new HttpGet(uri);
            configureRequest(config, get, timeout);

            return doRequest(config, timeout, get, requestHeaders, request, handler, expectedStatusCode);
        } catch (URISyntaxException e) {
            throw new SumoClientException("URI cannot be generated", e);
        }
    }

    public <Request extends HttpPostRequest, Response> Response
    post(ConnectionConfig config, String endpoint,
         Request request, Map<String, String> requestHeaders,
         ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            URI uri = new URIBuilder()
                    .setScheme(config.getProtocol())
                    .setHost(config.getHostname())
                    .setPort(config.getPort())
                    .setPath(getEndpointURI(endpoint))
                    .setCharset(StandardCharsets.UTF_8)
                    .build();
            HttpPost post = new HttpPost(uri);
            configureRequest(config, post, Defaults.DEFAULT_HTTP_TIMEOUT);

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, StandardCharsets.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            post.setEntity(entity);

            return doRequest(
                    config, post, requestHeaders, request, handler, expectedStatusCode);
        } catch (URISyntaxException e) {
            throw new SumoClientException("URI cannot be generated", e);
        } catch (UnsupportedEncodingException e) {
            throw new SumoClientException("Unsupported character encoding", e);
        } catch (JsonMappingException | JsonGenerationException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (IOException e) {
            throw new SumoClientException("Error generating JSON", e);
        }
    }

    public <Request extends HttpPutRequest, Response> Response
    put(ConnectionConfig config, String endpoint,
        Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            URI uri = new URIBuilder()
                    .setScheme(config.getProtocol())
                    .setHost(config.getHostname())
                    .setPort(config.getPort())
                    .setPath(getEndpointURI(endpoint))
                    .build();

            HttpPut put = new HttpPut(uri);

            Map<String, String> requestHeaders = new HashMap<String, String>();
            if (request.getETag() != null) {
                requestHeaders.put("If-Match", request.getETag());
            }

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, StandardCharsets.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            put.setEntity(entity);
            configureRequest(config, put, Defaults.DEFAULT_HTTP_TIMEOUT);

            return doRequest(
                    config, put, requestHeaders, request, handler, expectedStatusCode);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        } catch (UnsupportedEncodingException ex) {
            throw new SumoClientException("Unsupported character encoding", ex);
        } catch (JsonMappingException | JsonGenerationException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (IOException e) {
            throw new SumoClientException("Error generating JSON", e);
        }
    }

    public <Request extends HttpDeleteRequest, Response> Response
    delete(ConnectionConfig config, String endpoint,
           Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            URI uri = new URIBuilder()
                    .setScheme(config.getProtocol())
                    .setHost(config.getHostname())
                    .setPort(config.getPort())
                    .setPath(getEndpointURI(endpoint))
                    .build();
            HttpDelete delete = new HttpDelete(uri);
            configureRequest(config, delete, Defaults.DEFAULT_HTTP_TIMEOUT);

            Map<String, String> requestHeaders = HttpUtils.toRequestHeaders();

            return doRequest(
                    config, delete, requestHeaders, request, handler, expectedStatusCode);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        }
    }

    public static Map<String, String> toRequestHeaders(String... parts) {
        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < parts.length; i++) {
            result.put(parts[i], parts[++i]);
        }
        return result;
    }

    // Private methods


    private static String getEndpointURI(String endpoint) {
        return "/" + UrlParameters.API_SERVICE +
                "/" + UrlParameters.VERSION_PREFIX + API_VERSION +
                "/" + endpoint;
    }

    private <Request, Response> Response
    doRequest(ConnectionConfig config, HttpUriRequest method, Map<String, String> requestHeaders,
              Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {
        return doRequest(config, Defaults.DEFAULT_HTTP_TIMEOUT, method, requestHeaders, request, handler, expectedStatusCode);
    }

    private <Request, Response> Response
    doRequest(ConnectionConfig config, int timeout, HttpUriRequest uriRequest, Map<String, String> requestHeaders,
              Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        // Set headers
        for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
            uriRequest.setHeader(header.getKey(), header.getValue());
        }

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(config.getAuthScope(), config.getUsernamePasswordCredentials());
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        // NOTE(stefan, 2017-08-21): Pass in a long-lived authCache so that on subsequent calls we don't have to make
        // two requests.
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);

        InputStream httpStream = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(uriRequest, context);
            HttpEntity entity = httpResponse.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> handle http response
            if (httpResponse.getStatusLine().getStatusCode() == expectedStatusCode) {
                return handler.handle(httpResponse, httpStream, request);
            }

            // no -> get json error and throw exception
            else {
                StringWriter writer = new StringWriter();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpStream));

                // Convert response to JSON string
                for (String s; (s = reader.readLine()) != null; ) {
                    writer.write(s + "\n");
                }

                String json = writer.toString();
                if (JacksonUtils.isValidJson(json))
                    throw new SumoServerException(uriRequest.getURI().toString(), writer.toString());
                else
                    throw new SumoServerException(
                        uriRequest.getURI().toString(),
                        httpResponse.getStatusLine().getStatusCode());
            }
        }

        // Handle IO exceptions
        catch (IOException ex) {
            throw new SumoClientException("Error reading server response", ex);
        }

        // Handle runtime exceptions
        catch (RuntimeException ex) {
            if (ex instanceof SumoServerException) {
                throw ex;
            } else {
                throw new SumoClientException("Runtime error reading server response", ex);
            }
        }

        // Clean-up
        finally {
            if (httpStream != null) {
                try {
                    httpStream.close();
                } catch (Exception ex) {
                }
            }

            if (uriRequest != null) {
               try { uriRequest.abort();} catch (Exception ex) {}
            }

            try {
                httpResponse.close();
            }catch (Exception ex) {}

            try {
                httpClient.close();
            }catch (Exception ex) {}
        }
    }

    private void configureRequest(ConnectionConfig config, HttpRequestBase request, int timeout) {
        RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout);
        if (config.getProxy() != null) {
            builder.setProxy(config.getProxy()).build();
        }
        request.setConfig(builder.build());

    }
}
