package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;


public class SumoClient implements Sumo {
  private String sumoApiUrl = "service.sumologic.com";
  private Credential credential;
  private int responseReadBufferLength = 1024;

  public int getResponseReadBufferLength() {
    return responseReadBufferLength;
  }

  public void setResponseReadBufferLength(int responseReadBufferLength) {
    this.responseReadBufferLength = responseReadBufferLength;
  }

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

    SearchResponse searchResponse = null;
    HttpGet searchGetMethod = new HttpGet(URIUtils.createURI("https", sumoApiUrl, -1,
        "/api/v1/logs/search", query.formQueryUri(), null));

    HttpResponse response = httpClient.execute(searchGetMethod);
    HttpEntity entity = response.getEntity();
    Writer writer = new StringWriter();
    if (entity != null) {
      InputStream instream = entity.getContent();
      try {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(instream));
        char[] buffer = new char[responseReadBufferLength];
        int n;
        while ((n = reader.read(buffer)) != -1) {
          writer.write(buffer, 0, n);
        }
        if (response.getStatusLine().getStatusCode() == 200) {
          searchResponse = new SearchResponse(writer.toString());
          searchResponse.setSearchQueryUri(searchGetMethod.getURI().toString());
        } else {
          throw new ServerException(searchGetMethod.getURI().toString(), writer.toString());
        }
      } catch (IOException ex) {
        throw ex;
      } catch (RuntimeException ex) {
        searchGetMethod.abort();
        throw ex;
      } finally {
        instream.close();
      }

    }
    httpClient.getConnectionManager().shutdown();
    return searchResponse;
  }

}
