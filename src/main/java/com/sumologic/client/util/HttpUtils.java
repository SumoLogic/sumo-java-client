package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sumologic.client.*;
import com.sumologic.client.model.HttpDeleteRequest;
import com.sumologic.client.model.HttpGetRequest;
import com.sumologic.client.model.HttpPostRequest;
import com.sumologic.client.model.HttpPutRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpUtils {

    public static final int API_VERSION = 1;

    private static final String JSON_CONTENT_TYPE = "application/json";

    // Public HTTP request methods

    public static <Request extends HttpGetRequest, Response> Response
    get(ConnectionConfig config, String endpoint, Request request,
        ResponseHandler<Request, Response> handler) {

        try {
            String params = URLEncodedUtils.format(request.toUrlParams(), HTTP.UTF_8);
            URI uri = URIUtils.createURI(config.getProtocol(), config.getHostname(),
                    config.getPort(), getEndpointURI(endpoint), params, null);
            HttpGet get = new HttpGet(uri);

            return doRequest(config, get, request, handler);
        } catch (URISyntaxException e) {
            throw new SumoClientException("URI cannot be generated", e);
        }
    }

    public static <Request extends HttpPostRequest, Response> Response
    post(ConnectionConfig config, String endpoint, Request request,
         ResponseHandler<Request, Response> handler) {

        try {
            URI uri = URIUtils.createURI(config.getProtocol(), config.getHostname(),
                    config.getPort(), getEndpointURI(endpoint), null, null);
            HttpPost post = new HttpPost(uri);

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, HTTP.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            post.setEntity(entity);

            return doRequest(config, post, request, handler);
        } catch (URISyntaxException e) {
            throw new SumoClientException("URI cannot be generated", e);
        } catch (UnsupportedEncodingException e) {
            throw new SumoClientException("Unsupported character encoding", e);
        } catch (JsonMappingException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (JsonGenerationException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (IOException e) {
            throw new SumoClientException("Error generating JSON", e);
        }
    }

    public static <Request extends HttpPutRequest, Response> Response
    put(ConnectionConfig config, String endpoint, Request request,
        ResponseHandler<Request, Response> handler) {

        try {
            URI uri = URIUtils.createURI(config.getProtocol(), config.getHostname(),
                    config.getPort(), getEndpointURI(endpoint), null, null);
            HttpPut put = new HttpPut(uri);
            put.setHeader("If-Match", request.getETag());

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, HTTP.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            put.setEntity(entity);

            return doRequest(config, put, request, handler);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        } catch (UnsupportedEncodingException ex) {
            throw new SumoClientException("Unsupported character encoding", ex);
        } catch (JsonMappingException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (JsonGenerationException e) {
            throw new SumoClientException("Error generating JSON", e);
        } catch (IOException e) {
            throw new SumoClientException("Error generating JSON", e);
        }
    }

    public static <Request extends HttpDeleteRequest, Response> Response
    delete(ConnectionConfig config, String endpoint, Request request,
           ResponseHandler<Request, Response> handler) {

        try {
            URI uri = URIUtils.createURI(config.getProtocol(), config.getHostname(),
                    config.getPort(), getEndpointURI(endpoint), null, null);
            HttpDelete delete = new HttpDelete(uri);

            return doRequest(config, delete, request, handler);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        }
    }

    // Private methods

    private static HttpClient getHttpClient(ConnectionConfig config) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(config.getAuthScope(),
                config.getUsernamePasswordCredentials());
        return httpClient;
    }

    private static String getEndpointURI(String endpoint) {
        return "/" + UrlParameters.API_SERVICE +
                "/" + UrlParameters.VERSION_PREFIX + API_VERSION +
                "/" + endpoint;
    }

    private static <Request, Response> Response
    doRequest(ConnectionConfig config, HttpUriRequest method, Request request,
              ResponseHandler<Request, Response> handler) {

        HttpClient httpClient = getHttpClient(config);

        InputStream httpStream = null;
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            HttpEntity entity = httpResponse.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> handle http response
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
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

                throw new SumoServerException(method.getURI().toString(), writer.toString());
            }
        }

        // Handle IO exceptions
        catch (IOException ex) {
            throw new SumoClientException("Error reading server response", ex);
        }

        // Handle runtime exceptions
        catch (RuntimeException ex) {
            throw new SumoClientException("Runtime error reading server response", ex);
        }

        // Clean-up
        finally {
            if (httpStream != null) {
                try {
                    httpStream.close();
                } catch (IOException ex) {
                }
            }

            httpClient.getConnectionManager().shutdown();

            if (method != null) {
                method.abort();
            }
        }
    }

}
