package com.sumologic.client.metrics.model;

import org.joda.time.DateTime;

import java.util.HashMap;

public class Metric {
  private DateTime[] timestamps;
  private double[] values;
  private HashMap<String, String> dimensions = new HashMap<>();

  public DateTime[] getTimestamps() {
    return timestamps;
  }
  public void setTimestamps(DateTime[] timestamps) {
    this.timestamps = timestamps;
  }

  public double[] getValues() {
    return values;
  }
  public void setValues(double[] values) {
    this.values = values;
  }

  public void setDimensions(String key, String value) {
    dimensions.put(key, value);
  }
  public void setDimensions(HashMap<String, String> dimensions) {
    this.dimensions.putAll(dimensions);
  }

  public String getDimensions(String key) {
    return dimensions.get(key);
  }

  public String getDimensions() {
    StringBuffer sb = new StringBuffer();
    dimensions.forEach((k,v) -> sb.append("(").append(k).append(", ").append(v).append("), "));
    return sb.toString();
  }
}
