package com.sumologic.client;

public interface Sumo {
  void setCredential(Credential credential);
  SearchResponse search(SearchQuery searchQuery) throws Exception;

}
