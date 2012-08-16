package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The sumo client implementation.
 *
 * @author Sebastian Mies
 * @author Daphne Hsieh
 * @version 1.0
 */
public class SumoClient implements SumoLogs {

    /**
     * Constructs a Sumo Logic client.
     *
     * @param credential The credential used to access sumo logic's web service.
     */
    public SumoClient(Credential credential) {
        this.credential = credential;
    }

    /**
     * Convenience: constructs the credentials using email and password.
     *
     * @param email    Your email
     * @param password Your password
     */
    public SumoClient(String email, String password) {
        this.credential = new Credential(email, password);
    }

    /**
     * Sets a custom Sumo Logic API hostname, i.e.,
     * different from api.sumologic.com
     *
     * @param hostname The custom sumo logic api hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Sets the flag indicating whether https should be
     * used for connecting to the web-service.
     *
     * @param useHTTPs True, if HTTPS should be used (default).
     */
    public void setUseHTTPs(boolean useHTTPs) {
        this.useHTTPs = useHTTPs;
    }

    /**
     * Issues a search query using Sumo Logic's web service.
     *
     * @param query The search Query
     * @return The resulting log messages
     * @throws SumoException Client or server exception
     */
    public SearchResult search(SearchQuery query) throws SumoException {

        // Create http client and set credentials for HTTP auth
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, 443),
                new UsernamePasswordCredentials(credential.getEmail(), credential.getPassword()));

        // Try to issue query
        HttpGet searchGetMethod = null;
        InputStream httpStream = null;
        SearchResult searchResult = new SearchResult(query);
        try {
            // Issue http get request
            searchGetMethod = new HttpGet(
                    URIUtils.createURI(useHTTPs ? "https" : "http", hostname, -1,
                            "/" + Headers.API_SERVICE + "/" + Headers.VERSION_PREFIX + "1/" + Headers.LOGS_SERVICE + "/" + Headers.SEARCH, query.toString(), null)
            );
            HttpResponse response = httpClient.execute(searchGetMethod);
            HttpEntity entity = response.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> parse JSON to searchResponse
            if (response.getStatusLine().getStatusCode() == 200) {

                // Parse all JSON records
                JsonParser jp = jsonFactory.createJsonParser(httpStream);
                ArrayList<HashMap<String, String>> messages =
                        jp.readValueAs(new TypeReference<ArrayList<HashMap<String, String>>>() {
                        });

                // Add to result
                for (HashMap<String, String> message : messages) {
                    searchResult.getMessages().add(new LogMessage(message));
                }

                // Finished
                jp.close();
                httpClient.getConnectionManager().shutdown();
                return searchResult;

            }

            // no -> get json error and throw exception
            else {

                StringWriter writer = new StringWriter();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpStream));

                // Convert response to JSON string
                for (String s = null; (s = reader.readLine()) != null; )
                    writer.write(s + "\n");

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
            if (httpStream != null) try {
                httpStream.close();
            } catch (IOException io) {
            }
            if (searchGetMethod != null)
                searchGetMethod.abort();
        }
    }

    /**
     * Convenience function: takes a query string as argument
     *
     * @param query The sumo log query string
     * @return The search response
     * @throws SumoException On a client or server exception
     */
    public SearchResult search(String query) throws SumoException {
        return search(new SearchQuery(query));
    }

    private boolean useHTTPs = true;
    private String hostname = "service.sumologic.com";
    private Credential credential;
    private static JsonFactory jsonFactory = new JsonFactory();
}
