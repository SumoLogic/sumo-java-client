package com.sumologic.client;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.Map;

public class ServerException extends Exception{
  private int statusCode;
  private String errorId;
  private String errorCode;
  private String queryUri;
  private String message;

  public ServerException() {
    super();
    statusCode = -1;
  }

  public ServerException(String queryUri, String jsonString) throws Exception{
    super();
    this.queryUri = queryUri;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> errorResponseMap = mapper.readValue(
        jsonString, new TypeReference<Map<String,String>>() {});
    statusCode = Integer.parseInt(errorResponseMap.get("status"));
    errorId = errorResponseMap.get("id");
    errorCode = errorResponseMap.get("code");
    message = errorResponseMap.get("message");
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getErrorId() {
    return errorId;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getQueryUri() {
    return queryUri;
  }

  public String getMessage() {
    return message;
  }
}
