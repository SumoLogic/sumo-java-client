package com.sumologic.client;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SearchQuery {
  private String query = "";
  private Date fromTime = null;
  private Date toTime = null;
  private String timeZone = "";
  private String resultFormat = "";
  private List<NameValuePair> customParams = null;

  public String getQuery() {
    return query;
  }

  public SearchQuery setQuery(String query) {
    this.query = query;
    return this;
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

  public String getResultFormat() {
    return resultFormat;
  }

  public SearchQuery setResultFormat(String resultFormat) {
    this.resultFormat = resultFormat;
    return this;
  }

  public List<NameValuePair> getCustomParams() {
    return customParams;
  }

  public SearchQuery setCustomParams(List<NameValuePair> customParams) {
    this.customParams = customParams;
    return this;
  }

  public SearchQuery() {
  }

  public SearchQuery(String searchQuery) {
    query = searchQuery;
  }

  public String formQueryUri() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    if (customParams != null && !customParams.isEmpty()) {
      params.addAll(this.customParams);
    }
    params.add(new BasicNameValuePair("q", query));
    if (fromTime != null) {
      params.add(new BasicNameValuePair("from", String.valueOf(fromTime.getTime())));
    }
    if (toTime != null) {
      params.add(new BasicNameValuePair("to", String.valueOf(toTime.getTime())));
    }
    if (!timeZone.isEmpty()) {
      params.add(new BasicNameValuePair("tz", timeZone));
    }
    if (!resultFormat.isEmpty()) {
      params.add(new BasicNameValuePair("format", resultFormat));
    }
    return URLEncodedUtils.format(params, "UTF-8");
  }
}
