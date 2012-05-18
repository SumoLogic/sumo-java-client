package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResponse;
import org.junit.Test;

public class SumoClientTest {

  private SumoClient sumoClient;
  private Credential credential;
  private String userEmail;
  private String userPassword;


  @Test
  public void testSearch() throws Exception {
    userEmail = "daphy@demo.com";
    userPassword = "Whatever0987";

    credential = new Credential(userEmail, userPassword);
    sumoClient = new SumoClient(credential);
    //SearchQuery searchQuery = new SearchQuery("error");
    sumoClient.setSumoApiUrl("nite-api.sumologic.net");
    //Date currentTime = new Date();
    //Date oneHourBefore = new Date(currentTime.getTime() - 1000 * 60 * 60);
    //System.out.println(oneHourBefore.toString());
    //System.out.println(String.valueOf(oneHourBefore.getTime()));
    //List<NameValuePair> list = new ArrayList<NameValuePair>();
    //list.add(new BasicNameValuePair("to", "2012-05-16"));
    //list.add(new BasicNameValuePair("from", "2012-05-17"));
    SearchResponse response = sumoClient.search(
        new SearchQuery("error").setFromTimeISO8601("2012-05-17"));
    //    searchQuery.setResultFormat("json"));
    assert(response.getLogMessages().size() > 0);
    for(LogMessage log: response.getLogMessages()){
      System.out.println(log.getReceiptTime());
    }
  }

}
