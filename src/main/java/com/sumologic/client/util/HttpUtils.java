package com.sumologic.client.util;

import com.sumologic.client.*;
import com.sumologic.client.model.HttpGetRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.URISyntaxException;

public class HttpUtils {

    public static final int API_VERSION = 1;

    private static final String JSON_CONTENT_TYPE = "application/json";

    public static <Request extends HttpGetRequest, Response> Response
    httpGet(String protocol, String hostname, int port, Credentials credentials, String endpoint,
            Request request, ResponseHandler<Request, Response> handler) {

        try {
            String encodedParams = URLEncodedUtils.format(request.toUrlParams(), HTTP.UTF_8);
            HttpGet getMethod = new HttpGet(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), encodedParams, null));

            return doRequest(hostname, port, credentials, getMethod, request, handler);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        }
    }

    public static <Request, Response> Response
    httpPost(String protocol, String hostname, int port, Credentials credentials, String endpoint,
             Request request, ResponseHandler<Request, Response> handler) {

        try {
            HttpPost postMethod = new HttpPost(URIUtils.createURI(protocol, hostname + ":" + port,
                    -1, getEndpointURI(endpoint), null, null));

            StringEntity entity = new StringEntity(request.toString(), HTTP.UTF_8);
            entity.setContentType(JSON_CONTENT_TYPE);
            postMethod.setEntity(entity);

            return doRequest(hostname, port, credentials, postMethod, request, handler);
        } catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        } catch (UnsupportedEncodingException ex) {
            throw new SumoClientException("Unsupported character encoding", ex);
        }
    }

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
              Request request, ResponseHandler<Request, Response> handler) {

        HttpClient httpClient = HttpUtils.getHttpClient(hostname, port, credentials);

        InputStream httpStream = null;
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            HttpEntity entity = httpResponse.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> handle http response
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
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
            httpClient.getConnectionManager().shutdown();

            if (httpStream != null) {
                try {
                    httpStream.close();
                } catch (IOException ex) {
                }
            }
            if (method != null) {
                method.abort();
            }
        }
    }

}
