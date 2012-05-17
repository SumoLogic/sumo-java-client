package com.sumologic.client;

import java.util.Map;

public class LogMessage {
  private Map<String,String> rawMessageMap;

  public LogMessage(Map<String,String> rawMessageMap) {
    this.rawMessageMap = rawMessageMap ;
  }

  public String getRawMessage() {
    if(this.rawMessageMap.containsKey("_raw")) {
      return rawMessageMap.get("_raw");
    }
    return null;
  }

  public String getTime() {
    if(this.rawMessageMap.containsKey("time")) {
      return rawMessageMap.get("time");
    }
    return null;
  }
}
