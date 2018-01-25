package com.sumologic.client.metrics;

import org.apache.http.HttpResponse;
import com.sumologic.client.util.*;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;

public final class CreateMetricsJobResponse {

  private String id;
  private String error;
  private String errorMessage;
  private String response;
  private DateTime[] timestamps;
  private double[] values;

  /**
   * Returns the ID of the metrics job.
   *
   * @return The ID of the metrics job.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the ID of the metrics job.
   *
   * @param id The ID of the metrics job.
   */
  public void setId(String id) {
    this.id = id;
  }

  public String getError() {
    return error;
  }
  public void setError(String response) {
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

}

