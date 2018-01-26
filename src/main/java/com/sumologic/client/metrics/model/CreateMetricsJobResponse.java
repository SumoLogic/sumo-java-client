package com.sumologic.client.metrics.model;

import org.apache.http.HttpResponse;
import com.sumologic.client.util.*;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public final class CreateMetricsJobResponse implements Iterable<Metric> {

  private String id;
  private String error;
  private String errorMessage;
  private String response;
  private ArrayList<Metric> metrics = new ArrayList<>();

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

  @Override
  public Iterator<Metric> iterator() {
    return metrics.iterator();
  }

}

