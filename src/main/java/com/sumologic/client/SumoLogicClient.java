package com.sumologic.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.model.SearchRequest;
import com.sumologic.client.model.SearchResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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
    private String hostname = "service.sumologic.com";
    private Credentials credentials;
    private static JsonFactory jsonFactory = new JsonFactory();

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
    public SearchResponse search(SearchRequest request) {

        // Create http client and set credentials for HTTP auth
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port),
                new UsernamePasswordCredentials(credentials.getEmail(), credentials.getPassword()));

        // Try to issue query
        HttpGet searchGetMethod = null;
        InputStream httpStream = null;
        JsonParser jp = null;
        SearchResponse searchResponse = null;
        try {
            // Issue http get request
            searchGetMethod = new HttpGet(
                    URIUtils.createURI(
                            protocol,
                            hostname + ":" + port,
                            -1,
                            "/" + UrlParameters.API_SERVICE +
                                    "/" + UrlParameters.VERSION_PREFIX + "1" +
                                    "/" + UrlParameters.LOGS_SERVICE +
                                    "/" + UrlParameters.SEARCH,
                            request.toString(),
                            null
                    )
            );
            HttpResponse response = httpClient.execute(searchGetMethod);
            HttpEntity entity = response.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> parse JSON to searchResponse
            if (response.getStatusLine().getStatusCode() == 200) {

                // Parse all JSON records
                searchResponse = new SearchResponse(request);
                jp = jsonFactory.createJsonParser(httpStream);
                if (jp.nextToken() != JsonToken.START_ARRAY) {
                    return searchResponse;
                }
                while (jp.nextToken() == JsonToken.START_OBJECT) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String key = jp.getCurrentName();
                        jp.nextToken();
                        String value = jp.getText();
                        map.put(key, value);
                    }
                    searchResponse.getMessages().add( new LogMessage(map) );
                }
            }

            // no -> get json error and throw exception
            else {
                StringWriter writer = new StringWriter();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpStream));

                // Convert response to JSON string
                for (String s = null; (s = reader.readLine()) != null; ) {
                    writer.write(s + "\n");
                }

                throw new SumoServerException(
                        searchGetMethod.getURI().toString(),
                        writer.toString()
                );
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
            if (jp != null) try {
                jp.close();
            } catch (IOException io) {
            }
            httpClient.getConnectionManager().shutdown();

            if (httpStream != null) try {
                httpStream.close();
            } catch (IOException io) {
            }
            if (searchGetMethod != null)
                searchGetMethod.abort();
        }

        return searchResponse;
    }

    /**
     * Convenience function: takes a query string as argument
     *
     * @param query The sumo log query string
     * @return The search response
     */
    public SearchResponse search(String query) {
        return search(new SearchRequest(query));
    }
}
