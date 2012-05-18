package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class SumoClientTest {

  private static SumoClient sumoClient;

  @BeforeClass
  public static void oneTimeSetUp() {
    String userEmail = "daphy@demo.com";
    String userPassword = "XXXXXXX";

    Credential credential = new Credential(userEmail, userPassword);
    sumoClient = new SumoClient(credential);
    sumoClient.setSumoApiUrl("nite-api.sumologic.net");
  }

  @Test
  public void testDefaultSearchQuery() throws Exception {
    SearchResponse response = sumoClient.search(
        new SearchQuery("error"));
    assert (response.getLogMessages().size() > 0);
    System.out.println("Query: \n" + response.getSearchQueryUri() + "\nFirst batch Results:");
    for (LogMessage log : response.getLogMessages()) {
      System.out.println(log.getRawMessage());
    }
  }

  @Test
  public void testGettingCustomizedFieldInLogMessages() throws Exception {
    SearchResponse response = sumoClient.search(
        new SearchQuery("error"));
    assert (response.getLogMessages().size() > 0);
    System.out.println("Query: \n" + response.getSearchQueryUri() + "\nFirst batch Results:");
    for (LogMessage log : response.getLogMessages()) {
      System.out.println(log.getField("_sourcehost"));
    }
  }

  @Test
  public void testSearchForOneHour() throws Exception {
    Date currentTime = new Date();
    Date oneHourBefore = new Date(currentTime.getTime() - 1000 * 60 * 60);
    SearchResponse response = sumoClient.search(
        new SearchQuery("error").setFromTime(oneHourBefore).setToTime(currentTime));
    System.out.println("Query: \n" + response.getSearchQueryUri() + "\nFirst batch Results:");
    assert (response.getLogMessages().size() > 0);
    for (LogMessage log : response.getLogMessages()) {
      Date receiptTime = new Date(log.getReceiptTime());
      System.out.println(receiptTime.toString());
    }
  }
}
