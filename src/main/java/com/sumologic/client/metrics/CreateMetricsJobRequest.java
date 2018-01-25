package com.sumologic.client.metrics;

import com.sumologic.client.model.HttpPostRequest;
import org.joda.time.DateTime;

public final class CreateMetricsJobRequest implements HttpPostRequest {
  private String query;
  private String from;
  private String to;
  private String timeZone;
  private String rowId;
  private int requestedDataPoints = 600;
  private int maxDataPoints = 800;

  /**
   * Creates a metrics job request.
   *
   * @param query    The query.
   * @param from     The start of the time range.
   * @param to       The end of the time range.
   * @param timeZone The time zone.
   */
  public CreateMetricsJobRequest(String query,
                                String from,
                                String to,
                                String timeZone) {
    this.query = query;
    this.from = Long.toString(DateTime.parse(from).getMillis());
    this.to = Long.toString(DateTime.parse(to).getMillis());
    this.timeZone = timeZone;
    this.rowId = "A";
  }

  public CreateMetricsJobRequest(String query,
                                 DateTime from,
                                 DateTime to,
                                 int requestedDataPoints,
                                 int maxDataPoints) {
    this.query = query;
    this.from = Long.toString(from.getMillis());
    this.to = Long.toString(to.getMillis());
    if (!from.getZone().toString().equals(to.getZone().toString()))
      throw new IllegalArgumentException("From and to timestamps should be in the same timezone.");
    this.timeZone = from.getZone().toString();
    this.rowId = "A";
    this.requestedDataPoints = requestedDataPoints;
    this.maxDataPoints = maxDataPoints;
  }

  /**
   * Returns the query.
   *
   * @return The query.
   */
  public String getQuery() {
    return query;
  }

  /**
   * Sets the query.
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Sets the query.
   *
   * @return This object.
   */
  public CreateMetricsJobRequest withQuery(String query) {
    setQuery(query);
    return this;
  }

  /**
   * Returns the start of the time range.
   *
   * @return The start of the time range.
   */
  public String getFrom() {
    return from;
  }

  /**
   * Sets the start of the time range.
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * Sets the start of the time range.
   *
   * @param from The start of the time range.
   * @return This object.
   */
  public CreateMetricsJobRequest withFrom(String from) {
    setFrom(from);
    return this;
  }

  /**
   * Returns the end of the time range.
   *
   * @return The end of the time range.
   */
  public String getTo() {
    return to;
  }

  /**
   * Sets the end of the time range.
   */
  public void setTo(String to) {
    this.to = to;
  }

  /**
   * Sets the end of the time range.
   *
   * @param to The end of the time range.
   * @return This object.
   */
  public CreateMetricsJobRequest withTo(String to) {
    setTo(to);
    return this;
  }

  /**
   * Returns the time zone.
   *
   * @return The time zone.
   */
  public String getTimeZone() {
    return timeZone;
  }

  /**
   * Sets the time zone.
   */
  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  /**
   * Sets the time zone.
   *
   * @return This object.
   */
  public CreateMetricsJobRequest withTimeZone(String timeZone) {
    setTimeZone(timeZone);
    return this;
  }

  public String getRowId() {
    return rowId;
  }

  public int getRequestedDataPoints() {
    return requestedDataPoints;
  }

  public int getMaxDataPoints() {
    return maxDataPoints;
  }
}
