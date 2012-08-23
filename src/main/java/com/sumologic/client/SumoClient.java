package com.sumologic.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URISyntaxException;

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
     * <p/>
     * <p></p><b>Note:</b> This method resets the server
     * port to a default value (80/443).</p>
     *
     * @param useHTTPs True, if HTTPS should be used (default).
     */
    public void setUseHTTPs(boolean useHTTPs) {
        this.useHTTPs = useHTTPs;
        if (this.useHTTPs) port = 443;
        else port = 80;
    }

    /**
     * Sets the HTTP(s) port of sumo's log web service.
     *
     * @param port The HTTP(s) port.
     */
    public void setPort(int port) {
        this.port = port;
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
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port),
                new UsernamePasswordCredentials(credential.getEmail(), credential.getPassword()));

        // Try to issue query
        HttpGet searchGetMethod = null;
        InputStream httpStream = null;
        try {
            // Issue http get request
            searchGetMethod = new HttpGet(
                URIUtils.createURI(
                    useHTTPs ? "https" : "http",
                    hostname + (((port != 443 && useHTTPs) || (port != 80 && !useHTTPs)) ? ":" + port : ""),
                    -1,
                    "/" + Headers.API_SERVICE +
                            "/" + Headers.VERSION_PREFIX + "1" +
                            "/" + Headers.LOGS_SERVICE +
                            "/" + Headers.SEARCH,
                    query.toString(),
                    null
                )
            );
            HttpResponse response = httpClient.execute(searchGetMethod);
            HttpEntity entity = response.getEntity();
            httpStream = entity.getContent();

            // Request was ok? yes -> parse JSON to searchResponse
            if (response.getStatusLine().getStatusCode() == 200) {

                // Parse all JSON records
                SearchResult searchResult = new SearchResult(query);
                JsonParser jp = jsonFactory.createJsonParser(httpStream);
                if  (jp.nextToken() != JsonToken.START_ARRAY) {
                    return searchResult;
                }
                while (jp.nextToken() == JsonToken.START_OBJECT) {
                    LogMessage msg = new LogMessage();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String key = jp.getCurrentName();
                        jp.nextToken();
                        String value = jp.getText();
                        msg.getInternalMap().put(key, value);
                    }
                    searchResult.getMessages().add(msg);
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
    private int port = 443;
    private String hostname = "service.sumologic.com";
    private Credential credential;
    private static JsonFactory jsonFactory = new JsonFactory();
}
