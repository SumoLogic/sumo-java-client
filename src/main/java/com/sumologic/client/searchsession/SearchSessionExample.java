package com.sumologic.client.searchsession;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchsession.model.*;

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
                "| parse \"[Classification: *]\" as classification | count classification",
                "2013-01-18T20:00:00",
                "2013-01-18T21:00:00",
                "PST");
        System.out.printf("Search session ID: '%s'\n", searchSessionId);

        // Poll the search session status.
        int messageCount = 0;
        int recordCount = 0;
        GetSearchSessionStatusResponse getSearchSessionStatusResponse = null;
        while (getSearchSessionStatusResponse == null ||
                (!getSearchSessionStatusResponse.getState().equals("DONE GATHERING RESULTS") &&
                        !getSearchSessionStatusResponse.getState().equals("CANCELLED"))) {
            Thread.sleep(5000);
            getSearchSessionStatusResponse =
                    sumoClient.getSearchSessionStatus(searchSessionId);
            messageCount = getSearchSessionStatusResponse.getMessageCount();
            recordCount = getSearchSessionStatusResponse.getRecordCount();
            System.out.printf(
                    "Search session ID: '%s', %s\n",
                    searchSessionId,
                    getSearchSessionStatusResponse);
        }

        // Get some messages.
        int messageOffset = 0;
        int messageLength = Math.min(messageCount, 10);
        GetMessagesForSearchSessionResponse getMessagesForSearchSessionResponse =
                sumoClient.getMessagesForSearchSession(searchSessionId, messageOffset, messageLength);
        System.out.printf(
                "Messages for search session ID: '%s', %s\n",
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

        // Get some records.
        int recordOffset = 0;
        int recordLength = Math.min(recordCount, 10);
        GetRecordsForSearchSessionResponse getRecordsForSearchSessionResponse =
                sumoClient.getRecordsForSearchSession(searchSessionId, recordOffset, recordLength);
        System.out.printf(
                "Records for search session ID: '%s', %s\n",
                searchSessionId,
                getRecordsForSearchSessionResponse);
        List<SearchSessionRecord> records = getRecordsForSearchSessionResponse.getRecords();
        for (SearchSessionRecord record : records) {
            System.out.printf("  %s, %d\n",
                    record.stringField("classification"),
                    record.intField("_count"));
        }

        // Delete the session.
        CancelSearchSessionResponse cancelSearchSessionResponse =
                sumoClient.cancelSearchSession(searchSessionId);
    }
}