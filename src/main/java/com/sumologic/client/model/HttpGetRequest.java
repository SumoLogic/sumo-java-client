package com.sumologic.client.model;

import org.apache.http.NameValuePair;

import java.util.List;

public interface HttpGetRequest {

    List<NameValuePair> toUrlParams();
}
