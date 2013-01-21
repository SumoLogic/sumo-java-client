package com.sumologic.client.searchjob;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchjob.model.*;

import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchJobExample {

    public static void main(String[] args) throws Exception {

        String userEmail = "daddy@demo.com";
        String userPassword = "Rummel1";
        String apiUrl = "http://localhost:23667";

        Credentials credential = new Credentials(userEmail, userPassword);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(apiUrl);

        // Create a search job.
        String searchJobId = sumoClient.createSearchJob(
                "| parse \"[Classification: *]\" as classification | count classification",
                "2013-01-19T18:00:00",
                "2013-01-19T19:00:00",
                "PST");
        System.out.printf("Search job ID: '%s'\n", searchJobId);

        // Poll the search job status.
        int messageCount = 0;
        int recordCount = 0;
        GetSearchJobStatusResponse getSearchJobStatusResponse = null;
        while (getSearchJobStatusResponse == null ||
                (!getSearchJobStatusResponse.getState().equals("DONE GATHERING RESULTS") &&
                        !getSearchJobStatusResponse.getState().equals("CANCELLED"))) {
            Thread.sleep(5000);
            getSearchJobStatusResponse =
                    sumoClient.getSearchJobStatus(searchJobId);
            messageCount = getSearchJobStatusResponse.getMessageCount();
            recordCount = getSearchJobStatusResponse.getRecordCount();
            System.out.printf(
                    "Search job ID: '%s', %s\n",
                    searchJobId,
                    getSearchJobStatusResponse);
        }

        // Get some messages.
        int messageOffset = 0;
        int messageLength = Math.min(messageCount, 10);
        GetMessagesForSearchJobResponse getMessagesForSearchJobResponse =
                sumoClient.getMessagesForSearchJob(searchJobId, messageOffset, messageLength);
        System.out.printf(
                "Messages for search job ID: '%s', %s\n",
                searchJobId,
                getMessagesForSearchJobResponse);
        List<LogMessage> messages = getMessagesForSearchJobResponse.getMessages();
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
        GetRecordsForSearchJobResponse getRecordsForSearchJobResponse =
                sumoClient.getRecordsForSearchJob(searchJobId, recordOffset, recordLength);
        System.out.printf(
                "Records for search job ID: '%s', %s\n",
                searchJobId,
                getRecordsForSearchJobResponse);
        List<SearchJobRecord> records = getRecordsForSearchJobResponse.getRecords();
        for (SearchJobRecord record : records) {
            System.out.printf("  %s, %d\n",
                    record.stringField("classification"),
                    record.intField("_count"));
        }

        // Delete the search job.
        CancelSearchJobResponse cancelSearchJobResponse =
                sumoClient.cancelSearchJob(searchJobId);
    }
}