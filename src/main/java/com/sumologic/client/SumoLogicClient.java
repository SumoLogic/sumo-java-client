package com.sumologic.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sumologic.client.model.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sumologic.client.collectors.CollectorsClient;
import com.sumologic.client.collectors.model.*;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import com.sumologic.client.search.SearchClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * The sumo client implementation.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public class SumoLogicClient implements SumoLogic {

    private int port = 443;
    private String protocol = "https";
    private String hostname = "api.sumologic.com";
    private Credentials credentials;
    private static JsonFactory jsonFactory = new JsonFactory();

    private SearchClient searchClient = new SearchClient();
    private CollectorsClient collectorsClient = new CollectorsClient();

    /**
     * Constructs a Sumo Logic client.
     *
     * @param credentials The credential used to access sumo logic's web service.
     */
    public SumoLogicClient(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Convenience: constructs the credentials using email and password.
     *
     * @param email    Your email
     * @param password Your password
     */
    public SumoLogicClient(String email, String password) {
        this.credentials = new Credentials(email, password);
    }

    /**
     * Sets a custom Sumo Logic API url, i.e.,
     * different from https://api.sumologic.com
     *
     * @param urlString The custom sumo logic api URL
     * @throws MalformedURLException On URL syntax error
     */
    public void setURL(String urlString) throws MalformedURLException {
        URL url = new URL(urlString);
        this.hostname = url.getHost();
        this.port = (url.getPort() == -1) ?
                (url.getDefaultPort() == -1 ? 80 : url.getDefaultPort()) : url.getPort();
        this.protocol = url.getProtocol();
    }

    /**
     * Issues a search query using Sumo Logic's web service.
     *
     * @param request The search Query
     * @return The resulting log messages
     */
    @Override
    public SearchResponse search(SearchRequest request) {
        return searchClient.search(protocol, hostname, port, credentials, request);
    }

    /**
     * Convenience method: takes a query string as argument.
     *
     * @param query The sumo log query string
     * @return The search response
     */
    public SearchResponse search(String query) {
        return search(new SearchRequest(query));
    }

    @Override
    public String createSearchSession(CreateSearchSessionRequest createSearchSessionRequest) {

        // Get the HTTP client.
        DefaultHttpClient httpClient = getDefaultHttpClient();

        // Setup some stuff we need later.
        HttpPost httpMethod = null;
        InputStream responseEntityStream = null;
        JsonParser jp = null;
        String searchSessionId = null;

        try {

            // Construct the URI for the request.
            URI uri = URIUtils.createURI(
                    protocol,
                    hostname,
                    port,
                    "/" + UrlParameters.API_SERVICE +
                            "/" + UrlParameters.VERSION_PREFIX + "1" +
                            "/" + UrlParameters.SEARCH_SERVICE +
                            "/" + UrlParameters.SEARCH_SESSIONS_SERVICE,
                    null,
                    null);

            // Create the request method.
            httpMethod = new HttpPost(uri);

            // Set the relevant headers.
            httpMethod.setHeader("Content-type", "application/json");
            httpMethod.setHeader("Accept", "application/json");

            // Set the request body.
            String requestBody = createSearchSessionRequest.toJson();
            HttpEntity requestEntity = new StringEntity(requestBody);
            httpMethod.setEntity(requestEntity);

            // Fire, aim, ready.
            HttpResponse response = httpClient.execute(httpMethod);

            // Read the response body.
            HttpEntity responseEntity = response.getEntity();
            responseEntityStream = responseEntity.getContent();

            // Did we get the correct status code?
            if (response.getStatusLine().getStatusCode() == 201) {

                // Parse all JSON records
                jp = jsonFactory.createJsonParser(responseEntityStream);
                while (jp.nextToken() == JsonToken.START_OBJECT) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String key = jp.getCurrentName();
                        jp.nextToken();
                        String value = jp.getText();
                        map.put(key, value);
                    }
                    searchSessionId = map.get("id");
                }
            } else {

                // Read the response and create and throw an exception.
                String responseBody = EntityUtils.toString(responseEntity);
                throw new SumoServerException(
                        httpMethod.getURI().toString(),
                        responseBody);
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

        // Handle URI syntax exceptions
        catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        }

        // Clean-up
        finally {

            // Kill the JSON parser.
            if (jp != null) try {
                jp.close();
            } catch (IOException io) {
                // Yippee-kai-yay.
            }

            // Kill the stream.
            if (responseEntityStream != null) {
                try {
                    responseEntityStream.close();
                } catch (IOException io) {
                    // IOException is exceptional.
                }
            }

            // Kill the post method.
            if (httpMethod != null) {
                httpMethod.abort();
            }

            // Kill the connection.
            httpClient.getConnectionManager().shutdown();
        }

        return searchSessionId;
    }

    @Override
    public GetSearchSessionStatusResponse getSearchSessionStatus(String searchSessionId) {

        // Get the HTTP client.
        DefaultHttpClient httpClient = getDefaultHttpClient();

        // Setup some stuff we need later.
        HttpGet httpMethod = null;
        InputStream responseEntityStream = null;
        JsonParser jp = null;
        GetSearchSessionStatusResponse getSearchSessionStatusResponse = null;

        try {

            // Construct the URI for the request.
            URI uri = URIUtils.createURI(
                    protocol,
                    hostname,
                    port,
                    "/" + UrlParameters.API_SERVICE +
                            "/" + UrlParameters.VERSION_PREFIX + "1" +
                            "/" + UrlParameters.SEARCH_SERVICE +
                            "/" + UrlParameters.SEARCH_SESSIONS_SERVICE +
                            "/" + searchSessionId,
                    null,
                    null);

            // Create the request method.
            httpMethod = new HttpGet(uri);

            // Set the relevant headers.
            httpMethod.setHeader("Accept", "application/json");

            // Fire, aim, ready.
            HttpResponse response = httpClient.execute(httpMethod);

            // Read the response body.
            HttpEntity responseEntity = response.getEntity();
            responseEntityStream = responseEntity.getContent();

            // Did we get the correct status code?
            if (response.getStatusLine().getStatusCode() == 200) {

                // Parse all JSON records
                jp = jsonFactory.createJsonParser(responseEntityStream);
                while (jp.nextToken() == JsonToken.START_OBJECT) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String key = jp.getCurrentName();
                        jp.nextToken();
                        String value = jp.getText();
                        map.put(key, value);
                    }
                    getSearchSessionStatusResponse = new GetSearchSessionStatusResponse(
                            map.get("state"),
                            Integer.parseInt(map.get("messages")),
                            Integer.parseInt(map.get("records")));
                }
            } else {

                // Read the response and create and throw an exception.
                String responseBody = EntityUtils.toString(responseEntity);
                throw new SumoServerException(
                        httpMethod.getURI().toString(),
                        responseBody);
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

        // Handle URI syntax exceptions
        catch (URISyntaxException ex) {
            throw new SumoClientException("URI cannot be generated", ex);
        }

        // Clean-up
        finally {

            // Kill the JSON parser.
            if (jp != null) try {
                jp.close();
            } catch (IOException io) {
                // Yippee-kai-yay.
            }

            // Kill the stream.
            if (responseEntityStream != null) {
                try {
                    responseEntityStream.close();
                } catch (IOException io) {
                    // IOException is exceptional.
                }
            }

            // Kill the post method.
            if (httpMethod != null) {
                httpMethod.abort();
            }

            // Kill the connection.
            httpClient.getConnectionManager().shutdown();
        }

        return getSearchSessionStatusResponse;
    }

    // Private Implementation.

    private DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(hostname, port),
                new UsernamePasswordCredentials(
                        credentials.getEmail(),
                        credentials.getPassword()));
        return httpClient;
    }

    /**
     * Gets all available Sumo Logic collectors matching the request.
     *
     * @param request The request
     * @return The response
     */
    public GetCollectorsResponse getCollectors(GetCollectorsRequest request) {
        return collectorsClient.get(protocol, hostname, port, credentials, request);
    }

    /**
     * Gets all available Sumo Logic collectors.
     *
     * @return The response
     */
    public GetCollectorsResponse getCollectors() {
        return getCollectors(new GetCollectorsRequest());
    }

    /**
     * Gets a single Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public GetCollectorResponse getCollector(GetCollectorRequest request) {
        return collectorsClient.get(protocol, hostname, port, credentials, request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    public GetCollectorResponse getCollector(Long id) {
        return getCollector(new GetCollectorRequest(id));
    }

    /**
     * Updates a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public UpdateCollectorResponse updateCollector(UpdateCollectorRequest request) {
        return collectorsClient.update(protocol, hostname, port, credentials, request);
    }

    /**
     * Convenience method: takes a collector as argument.
     *
     * @param collector The collector
     * @return The response
     */
    public UpdateCollectorResponse updateCollector(Collector collector) {
        return updateCollector(new UpdateCollectorRequest(collector.getId(), collector));
    }

    /**
     * Deletes a Sumo Logic collector.
     *
     * @param request The request
     * @return The response
     */
    public DeleteCollectorResponse deleteCollector(DeleteCollectorRequest request) {
        return collectorsClient.delete(protocol, hostname, port, credentials, request);
    }

    /**
     * Convenience method: takes an id as argument.
     *
     * @param id The id
     * @return The response
     */
    public DeleteCollectorResponse deleteCollector(Long id) {
        return deleteCollector(new DeleteCollectorRequest(id));
    }
}
