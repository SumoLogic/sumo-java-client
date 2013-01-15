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
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static final int API_VERSION = 1;

    private static final String JSON_CONTENT_TYPE = "application/json";

    // Public HTTP request methods

    public static <Request extends HttpGetRequest, Response> Response
    get(String protocol, String hostname, int port, Credentials credentials, String endpoint,
        Request request, Map<String, String> requestHeaders,
        ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            String encodedParams = URLEncodedUtils.format(request.toUrlParams(), HTTP.UTF_8);
            HttpGet get = new HttpGet(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), encodedParams, null));

            // Set the request headers.
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                get.setHeader(header.getKey(), header.getValue());
            }

            return doRequest(
                    hostname, port, credentials, get, request, handler, expectedStatusCode);
        } catch (URISyntaxException e) {
            throw new SumoClientException("URI cannot be generated", e);
        }
    }

    public static <Request extends HttpPostRequest, Response> Response
    post(String protocol, String hostname, int port, Credentials credentials, String endpoint,
         Request request, Map<String, String> requestHeaders,
         ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            HttpPost post = new HttpPost(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), null, null));

            // Set the request headers.
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                post.setHeader(header.getKey(), header.getValue());
            }

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, HTTP.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            post.setEntity(entity);

            return doRequest(
                    hostname, port, credentials, post, request, handler, expectedStatusCode);
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
    put(String protocol, String hostname, int port, Credentials credentials, String endpoint,
        Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            HttpPut put = new HttpPut(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), null, null));

            String body = JacksonUtils.MAPPER.writeValueAsString(request);
            StringEntity entity = new StringEntity(body, HTTP.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            put.setEntity(entity);

            return doRequest(
                    hostname, port, credentials, put, request, handler, expectedStatusCode);
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
    delete(String protocol, String hostname, int port, Credentials credentials, String endpoint,
           Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        try {
            HttpDelete delete = new HttpDelete(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), null, null));

            return doRequest(
                    hostname, port, credentials, delete, request, handler, expectedStatusCode);
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

    private static HttpClient getHttpClient(String hostname, int port, Credentials credentials) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port),
                new UsernamePasswordCredentials(credentials.getEmail(), credentials.getPassword()));
        return httpClient;
    }

    private static String getEndpointURI(String endpoint) {
        return "/" + UrlParameters.API_SERVICE +
                "/" + UrlParameters.VERSION_PREFIX + API_VERSION +
                "/" + endpoint;
    }

    private static <Request, Response> Response
    doRequest(String hostname, int port, Credentials credentials, HttpUriRequest method,
              Request request, ResponseHandler<Request, Response> handler, int expectedStatusCode) {

        HttpClient httpClient = getHttpClient(hostname, port, credentials);

        InputStream httpStream = null;
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            HttpEntity entity = httpResponse.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> handle http response
            if (httpResponse.getStatusLine().getStatusCode() == expectedStatusCode) {
                return handler.handle(httpStream, request);
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
