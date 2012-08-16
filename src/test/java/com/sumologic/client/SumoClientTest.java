package com.sumologic.client;

import com.sumologic.client.model.SearchQuery;
import com.sumologic.client.model.SearchResult;
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
        sumoClient.setHostname("nite-api.sumologic.net");
    }

    @Test
    public void testDefaultSearchQuery() throws Exception {
        SearchResult result = sumoClient.search(
                new SearchQuery("error"));
        assert (result.size() > 0);
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        for (LogMessage msg : result.getMessages()) System.out.println(msg);
    }

    @Test
    public void testGettingCustomizedFieldInLogMessages() throws SumoException {
        SearchResult result = sumoClient.search(new SearchQuery("error"));
        assert (result.size() > 0);
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        for (LogMessage log : result.getMessages()) System.out.println(log.getSourceHost());
    }

    @Test
    public void testSearchForOneHour() throws SumoException {
        Date currentTime = new Date();
        Date oneHourBefore = new Date(currentTime.getTime() - 1000 * 60 * 60);
        SearchResult result = sumoClient.search(
                new SearchQuery("error").setFromTime(oneHourBefore).setToTime(currentTime));
        System.out.println("Query: \n" + result.getQuery() + "\nFirst batch Results:");
        assert (result.getMessages().size() > 0);
        for (LogMessage log : result.getMessages()) {
            System.out.println(log.getReceiptTime());
        }
    }
}
