package com.sumologic.client.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;


public class SearchQuery {
  private String query = "";
  private Date fromTime = null;
  private String fromTimeISO8601 = "";
  private Date toTime = null;
  private String toTimeISO8601 = "";
  private String timeZone = "";

  public SearchQuery() {
  }

  public SearchQuery(String searchQuery) {
    query = searchQuery;
  }


  public String getQuery() {
    return query;
  }

  public SearchQuery setQuery(String query) {
    this.query = query;
    return this;
  }

  public String getFromTimeISO8601() {
    return fromTimeISO8601;
  }

  public SearchQuery setFromTimeISO8601(String fromTimeISO8601) {
    this.fromTimeISO8601 = fromTimeISO8601;
    return this;
  }

  public String getToTimeISO8601() {
    return toTimeISO8601;
  }

  public void setToTimeISO8601(String toTimeISO8601) {
    this.toTimeISO8601 = toTimeISO8601;
  }

  public Date getFromTime() {
    return fromTime;
  }

  public SearchQuery setFromTime(Date fromTime) {
    this.fromTime = fromTime;
    return this;
  }

  public Date getToTime() {
    return toTime;
  }

  public SearchQuery setToTime(Date toTime) {
    this.toTime = toTime;
    return this;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public SearchQuery setTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public String formQueryUri() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("q", query));
    params.add(new BasicNameValuePair("format", "json")); // SearchResponse only accept json.
    if (fromTime != null) {
      params.add(new BasicNameValuePair("from", String.valueOf(fromTime.getTime())));
    }
    if (toTime != null) {
      params.add(new BasicNameValuePair("to", String.valueOf(toTime.getTime())));
    }
    if (!timeZone.isEmpty()) {
      params.add(new BasicNameValuePair("tz", timeZone));
    }
    if (!fromTimeISO8601.isEmpty()) {
      params.add(new BasicNameValuePair("from", fromTimeISO8601));
    }
    if(!toTimeISO8601.isEmpty()) {
      params.add(new BasicNameValuePair("to", toTimeISO8601));
    }
    return URLEncodedUtils.format(params, "UTF-8");
  }
}
