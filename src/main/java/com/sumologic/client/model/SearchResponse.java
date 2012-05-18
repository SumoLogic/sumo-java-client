package com.sumologic.client.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sumologic.client.LogMessage;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class SearchResponse {

  private List<LogMessage> logMessages;
  private String searchQueryUri;

  public String getSearchQueryUri() {
    return searchQueryUri;
  }

  public void setSearchQueryUri(String searchQueryUri) {
    this.searchQueryUri = searchQueryUri;
  }


  public List<LogMessage> getLogMessages() {
    return logMessages;
  }

  public SearchResponse() {
  }

  public SearchResponse(String response) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      logMessages = new ArrayList<LogMessage>();
      List<Map<String, String>> messages =
          mapper.readValue(response, new TypeReference<List<Map<String, String>>>() {
          });
      for(Map<String,String> message : messages) {
        logMessages.add(new LogMessage(message));
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
