package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResponse;

public interface Sumo {
  void setCredential(Credential credential);
  SearchResponse search(SearchQuery searchQuery) throws Exception;
}
