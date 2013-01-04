package com.sumologic.client.util;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoClientException;
import com.sumologic.client.SumoServerException;
import com.sumologic.client.UrlParameters;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URISyntaxException;

public class HttpUtils {

    public static <Request, Response> Response
    httpGet(String protocol, String hostname, int port, Credentials credentials, String endpoint,
            Request request, ResponseHandler<Request, Response> handler) {

        HttpClient httpClient = HttpUtils.getHttpClient(hostname, port, credentials);

        HttpGet getMethod = null;
        InputStream httpStream = null;
        try {
            // Issue http get request
            getMethod = new HttpGet(URIUtils.createURI(protocol, hostname + ":" + port, -1,
                    getEndpointURI(endpoint), request.toString(), null));

            HttpResponse httpResponse = httpClient.execute(getMethod);
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
                for (String s = null; (s = reader.readLine()) != null; ) {
                    writer.write(s + "\n");
                }

                throw new SumoServerException(getMethod.getURI().toString(), writer.toString());
            }
        }

        // Handle URI syntax exceptions
        catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
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
            if (getMethod != null) {
                getMethod.abort();
            }
        }
    }

    private static HttpClient getHttpClient(String hostname, int port, Credentials credentials) {
        // Create http client and set credentials for HTTP auth
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port),
                new UsernamePasswordCredentials(credentials.getEmail(), credentials.getPassword()));
        return httpClient;
    }

    private static String getEndpointURI(String endpoint) {
        return "/" + UrlParameters.API_SERVICE + "/" + endpoint;
    }

    public interface ResponseHandler<Request, Response> {

        public Response handle(InputStream httpStream, Request request) throws IOException;
    }
}
