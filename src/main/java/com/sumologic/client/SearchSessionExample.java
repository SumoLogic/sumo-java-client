package com.sumologic.client;

import com.sumologic.client.model.CreateSearchSessionRequest;
import com.sumologic.client.model.GetSearchSessionStatusResponse;

public class SearchSessionExample {

    public static void main(String[] args) throws Exception {

        String userEmail = "daddy@demo.com";
        String userPassword = "Rummel1";
        String apiUrl = "http://localhost:23667";

        Credentials credential = new Credentials(userEmail, userPassword);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(apiUrl);

        // Create a search session.
        CreateSearchSessionRequest createSearchSessionRequest =
                new CreateSearchSessionRequest(
                        "*",
                        "2013-01-06T11:00:00",
                        "2013-01-06T12:00:00",
                        "UTC");
        String searchSessionId = sumoClient.createSearchSession(createSearchSessionRequest);
        System.out.printf("Search session ID: '%s'\n", searchSessionId);

        // Poll the search session status.
        GetSearchSessionStatusResponse getSearchSessionStatusResponse = null;
        while (getSearchSessionStatusResponse == null ||
                (!getSearchSessionStatusResponse.getState().equals("DONE GATHERING RESULTS") &&
                        !getSearchSessionStatusResponse.getState().equals("CANCELLED"))) {
            Thread.sleep(5000);
            getSearchSessionStatusResponse =
                    sumoClient.getSearchSessionStatus(searchSessionId);
            System.out.printf(
                    "Search session ID: '%s', %s\n",
                    searchSessionId,
                    getSearchSessionStatusResponse);
        }
    }
}