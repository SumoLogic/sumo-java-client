package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ResponseHandler;


public class SumoClient implements Sumo {
  private String sumoApiUrl = "service.sumologic.com";
  private Credential credential;

  public SumoClient(Credential credential) {
    this.credential = credential;
  }

  public String getSumoApiUrl() {
    return sumoApiUrl;
  }

  public void setSumoApiUrl(String url) {
    this.sumoApiUrl = url;
  }

  public SearchResponse search(SearchQuery query) throws Exception {
    DefaultHttpClient httpClient = new DefaultHttpClient();
    httpClient.getCredentialsProvider().setCredentials(
        new AuthScope(sumoApiUrl, 443),
        new UsernamePasswordCredentials(credential.getEmail(), credential.getPassword()));

    SearchResponse response = null;
    HttpGet searchGetMethod = new HttpGet(URIUtils.createURI("https", sumoApiUrl, -1,
        "/api/v1/logs/search", query.formQueryUri(), null));
    System.out.println(searchGetMethod.getURI());
    try {
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = httpClient.execute(searchGetMethod, responseHandler);
      response = new SearchResponse(responseBody);
    } finally {
      searchGetMethod.abort();
    }
    return response;
  }

}
