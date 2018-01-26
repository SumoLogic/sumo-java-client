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

  /**
   * Returns the Session ID of the search job.
   *
   * @return The Session ID of the search job.
   */
  public String getSessionId() {
    return sessionId;
  }

  /**
   * Sets the Session ID of the search job.
   *
   * @param sessionId The Session ID of the search job.
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * Returns the error string, if any, of the metrics job.
   *
   * @return The error string of the metrics job.
   */
  public String getError() {
    return error;
  }

  /**
   * Sets the error string of the metrics job.
   *
   * @param error The error string of the metrics job.
   */
  public void setError(String error) {
    this.error = error;
  }

  /**
   * Returns the error message, if any, of the metrics job.
   *
   * @return The error message of the metrics job.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets the error message of the metrics job.
   *
   * @param errorMessage The error message of the metrics job.
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Returns the response json of the metrics job.
   *
   * @return The response json of the metrics job.
   */
  public String getResponse() {
    return response;
  }

  /**
   * Sets the response json of the metrics job.
   *
   * @param response The response json of the metrics job.
   */
  public void setResponse(String response) {
    this.response = response;
  }

  /**
   * Adds a metric to the response the metrics job.
   *
   * @param metric The metric to be added to the metrics job.
   */
  public void addMetric(Metric metric) {
    metrics.add(metric);
  }

  /**
   * Sets the start time of the metrics job as received from the backend.
   *
   * @param startTime The start time of the metrics job.
   */
  public void setStartTime(long startTime) {
    this.startTime = new DateTime(startTime);
  }

  /**
   * Returns the start time of the metrics job as received from the backend.
   *
   * @return The start time of the metrics job.
   */
  public DateTime getStartTime() {
    return startTime;
  }

  /**
   * Sets the end time of the metrics job as received from the backend.
   *
   * @param endTime The end time of the metrics job.
   */
  public void setEndTime(long endTime) {
    this.endTime = new DateTime(endTime);
  }

  /**
   * Returns the end time of the metrics job as received from the backend.
   *
   * @return The end time of the metrics job.
   */
  public DateTime getEndTime() {
    return endTime;
  }

  /**
   * Returns an iterator over all metrics in the response.
   *
   * @return Iterator over all metrics in the response.
   */
  @Override
  public Iterator<Metric> iterator() {
    return metrics.iterator();
  }
}
