package com.sumologic.client.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;


public class SearchQuery {
  private String query = "";
  private Date fromTime = null;
  private Date toTime = null;
  private String timeZone = "";
  private String resultFormat = "";
  private Map<String, String> customParamsMap;

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

  public Map<String, String> getCustomParamsMap() {
    return customParamsMap;
  }

  public SearchQuery setCustomParams(Map<String, String> customParamsMap) {
    this.customParamsMap = customParamsMap;
    return this;
  }

  public SearchQuery addCustomParams(Map<String, String> customParamsMap) {
    if (this.customParamsMap == null) {
      this.customParamsMap = customParamsMap;
    } else {
      this.customParamsMap.putAll(customParamsMap);
    }
    return this;
  }

  public SearchQuery addCustomParam(String key, String value) {
    if (this.customParamsMap == null) {
      this.customParamsMap = new HashMap<String, String>();
    }
    this.customParamsMap.put(key, value);
    return this;
  }

  public SearchQuery() {
  }

  public SearchQuery(String searchQuery) {
    query = searchQuery;
  }

  public String formQueryUri() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("q", query));
    if (this.customParamsMap != null) {
      for (Map.Entry<String, String> param : this.customParamsMap.entrySet()) {
        params.add(new BasicNameValuePair(param.getKey(), param.getValue()));
      }
    }
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
