package com.sumologic.client.metrics.model;

import org.apache.http.HttpResponse;
import com.sumologic.client.util.*;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public final class CreateMetricsJobResponse implements Iterable<Metric> {

  private String sessionId;
  private String error;
  private String errorMessage;
  private String response;
  private ArrayList<Metric> metrics = new ArrayList<>();
  private DateTime startTime;
  private DateTime endTime;

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public void addMetric(Metric metric) {
    metrics.add(metric);
  }

  public void setStartTime(long startTime) {
    this.startTime = new DateTime(startTime);
  }

  public DateTime getStartTime() {
    return startTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = new DateTime(endTime);
  }

  public DateTime getEndTime() {
    return endTime;
  }

  @Override
  public Iterator<Metric> iterator() {
    return metrics.iterator();
  }
}
