package com.sumologic.client.searchsession;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchsession.model.CancelSearchSessionResponse;
import com.sumologic.client.searchsession.model.GetMessagesForSearchSessionResponse;
import com.sumologic.client.searchsession.model.GetSearchSessionStatusResponse;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchSessionExample {

    public static void main(String[] args) throws Exception {

        String userEmail = "daddy@demo.com";
        String userPassword = "Rummel1";
        String apiUrl = "http://localhost:23667";

        Credentials credential = new Credentials(userEmail, userPassword);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(apiUrl);

        // Create a search session.
        String searchSessionId = sumoClient.createSearchSession(
                "*",
                "2013-01-13T00:00:00",
                "2013-01-15T00:00:00",
                "UTC");
        System.out.printf("Search session ID: '%s'\n", searchSessionId);

        // Poll the search session status.
        int messageCount = 0;
        GetSearchSessionStatusResponse getSearchSessionStatusResponse = null;
        while (getSearchSessionStatusResponse == null ||
                (!getSearchSessionStatusResponse.getState().equals("DONE GATHERING RESULTS") &&
                        !getSearchSessionStatusResponse.getState().equals("CANCELLED"))) {
            Thread.sleep(5000);
            getSearchSessionStatusResponse =
                    sumoClient.getSearchSessionStatus(searchSessionId);
            messageCount = getSearchSessionStatusResponse.getMessageCount();
            System.out.printf(
                    "Search session ID: '%s', %s\n",
                    searchSessionId,
                    getSearchSessionStatusResponse);
        }

        // Get some messages.
        int offset = 0;
        int length = Math.min(messageCount, 10);
        GetMessagesForSearchSessionResponse getMessagesForSearchSessionResponse =
                sumoClient.getMessagesForSearchSession(searchSessionId, offset, length);
        System.out.printf(
                "Search session ID: '%s', %s\n",
                searchSessionId,
                getMessagesForSearchSessionResponse);
        List<LogMessage> messages = getMessagesForSearchSessionResponse.getMessages();
        for (LogMessage message : messages) {
            System.out.printf("  %s, %s, %s, %s, %s\n",
                    message.getTime(),
                    message.getSourceHost(),
                    message.getSourceName(),
                    message.getSourceCategory(),
                    message.getLogLine());
        }

        // Delete the session.
        CancelSearchSessionResponse cancelSearchSessionResponse =
                sumoClient.cancelSearchSession(searchSessionId);
    }
}