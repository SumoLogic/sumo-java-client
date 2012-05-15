package com.sumologic.client;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/11/12
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */

public class SearchResponse {

  private List<Map<String,Object>> logMessages;

  public List<Map<String, Object>> getLogMessages() {
    return logMessages;
  }

  public SearchResponse() {
  }

  public SearchResponse(String response) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      logMessages = mapper.readValue(response, new TypeReference<List<Map<String,Object>>>(){});
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
