package com.sumologic.client;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/11/12
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Sumo {
  void setCredential(Credential credential);
  SearchResponse search(SearchQuery searchQuery) throws Exception;

}
