package com.sumologic.client;

import java.util.Map;

public class LogMessage {
  private Map<String,String> rawMessageMap;

  public String getField(String fieldName) {
    if(this.rawMessageMap.containsKey(fieldName)) {
      return rawMessageMap.get(fieldName);
    }
    return null;
  }

  public long getFieldInLong(String fieldName) {
    String longStr = this.getField(fieldName);
    if (longStr != null) {
      return Long.parseLong(longStr);
    }
    return -1;
  }

  public LogMessage(Map<String,String> rawMessageMap) {
    this.rawMessageMap = rawMessageMap ;
  }

  public String getRawMessage() {
    return this.getField("_raw");
  }


  public long getReceiptTime() {
    return this.getFieldInLong("_receipttime");
  }

  public long getMessageTime() {
    return this.getFieldInLong("_messagetime");
  }

}
