package com.sumologic.client.examples;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoException;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.search.model.LogMessage;
import com.sumologic.client.search.model.SearchRequest;
import com.sumologic.client.search.model.SearchResponse;

import java.net.MalformedURLException;
import java.util.Date;

public class SumoClientExamples {

    private static SumoLogicClient sumoClient;

    //    @BeforeClass
    public static void oneTimeSetUp() {
        String userEmail = "username";
        String userPassword = "password";

        Credentials credential = new Credentials(userEmail, userPassword);
        sumoClient = new SumoLogicClient(credential);
        try {
            sumoClient.setURL("https://api.sumologic.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //    @Test
    public void testDefaultSearchQuery() throws Exception {
        SearchResponse result = sumoClient.search(new SearchRequest("error"));
        assert (result.size() > 0);
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        for (LogMessage msg : result.getMessages()) System.out.println(msg);
    }

    //    @Test
    public void testGettingCustomizedFieldInLogMessages() throws SumoException {
        SearchResponse result = sumoClient.search(new SearchRequest("error"));
        assert (result.size() > 0);
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        for (LogMessage log : result.getMessages()) System.out.println(log.getSourceHost());
    }

    //    @Test
    public void testSearchForOneHour() throws SumoException {
        Date currentTime = new Date();
        Date oneHourBefore = new Date(currentTime.getTime() - 1000 * 60 * 60);
        SearchResponse result = sumoClient.search(
                new SearchRequest("error").withFromTime(oneHourBefore).withToTime(currentTime));
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        assert (result.getMessages().size() > 0);
        for (LogMessage log : result.getMessages()) {
            System.out.println(log.getReceiptTime());
        }
    }
}
