package com.sumologic.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/7/12
 */


public class SumoClient implements Sumo {
  private static SumoClient ourInstance = new SumoClient();
  private static DefaultHttpClient httpClient = new DefaultHttpClient();
  private static String sumoApiUrl = "nite-api.sumologic.net";


  public void setCredential(Credential credential) {
    httpClient.getCredentialsProvider().setCredentials(
        new AuthScope(sumoApiUrl, 443),
        new UsernamePasswordCredentials(credential.getEmail(), credential.getPassword()));
  }

  public SearchResponse search(SearchQuery query) throws Exception {
    SearchResponse response = null;
    HttpGet searchGetMethod = new HttpGet(URIUtils.createURI("https", sumoApiUrl, -1,
        "/api/v1/logs/search", query.formQueryUri(), null));

    try {
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = httpClient.execute(searchGetMethod, responseHandler);
      response = new SearchResponse(responseBody);
    } finally {
      searchGetMethod.abort();
    }
    return response;
  }

  public void

  public static SumoClient getInstance() {
    return ourInstance;
  }

  public SumoClient() {
  }

  public SumoClient(Credential credential) {
    this.setCredential(credential);
  }

}
