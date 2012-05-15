package com.sumologic.client;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/10/12
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */

public class SearchQuery {
  private String query = "";
  private String fromTime = "";
  private String toTime = "";
  private String timeZone = "";
  private String resultFormat = "";

  public String getQuery() {
    return query;
  }

  public SearchQuery setQuery(String query) {
    this.query = query;
    return this;
  }

  public String getFromTime() {
    return fromTime;
  }

  public SearchQuery setFromTime(String fromTime) {
    this.fromTime = fromTime;
    return this;
  }

  public String getToTime() {
    return toTime;
  }

  public SearchQuery setToTime(String toTime) {
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

  public String getResultFormat() {
    return resultFormat;
  }

  public SearchQuery setResultFormat(String resultFormat) {
    this.resultFormat = resultFormat;
    return this;
  }

  public SearchQuery(){
  }

  public SearchQuery(String searchQuery) {
    query = searchQuery;
  }

  public String formQueryUri() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("q", query));
    if (!fromTime.isEmpty()) {
      params.add(new BasicNameValuePair("from", fromTime));
    }
    if (!toTime.isEmpty()) {
      params.add(new BasicNameValuePair("to", fromTime));
    }
    if (!timeZone.isEmpty()) {
      params.add(new BasicNameValuePair("tz", fromTime));
    }
    if (!resultFormat.isEmpty()) {
      params.add(new BasicNameValuePair("format", resultFormat));
    }
    return URLEncodedUtils.format(params, "UTF-8");
  }
}
