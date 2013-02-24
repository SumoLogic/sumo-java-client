package com.sumologic.client.searchjob;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchjob.model.GetMessagesForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetSearchJobStatusResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchJobResultDumper {

    public static void main(String[] args) throws Exception {

        String url = args[0];

        String email = args[1];
        String password = args[2];

        Credentials credential = new Credentials(email, password);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(url);

        String searchQuery = args[3];
        String startTimestamp = args[4];
        String endTimestamp = args[5];
        String timeZone = args[6];

        String searchJobId = sumoClient.createSearchJob(
                searchQuery,
                startTimestamp,
                endTimestamp,
                timeZone);

        System.err.printf("Search job ID: '%s'\n", searchJobId);

        try {

            int messageCount = 0;
            int recordCount = 0;
            int messageOffset = 0;
            GetSearchJobStatusResponse getSearchJobStatusResponse = null;
            while (getSearchJobStatusResponse == null ||
                    (!getSearchJobStatusResponse.getState().equals("DONE GATHERING RESULTS") &&
                            !getSearchJobStatusResponse.getState().equals("CANCELLED"))) {

                long startMillis = System.currentTimeMillis();

                getSearchJobStatusResponse = sumoClient.getSearchJobStatus(searchJobId);
                messageCount = getSearchJobStatusResponse.getMessageCount();
                recordCount = getSearchJobStatusResponse.getRecordCount();
                System.err.printf(
                        "Search job ID: '%s', messages: '%d', records: '%d'\n",
                        searchJobId, messageCount, recordCount);

                int messageLength = 0;
                while ((messageLength = messageCount - messageOffset) > 0) {
                    messageLength = Math.min(messageLength, 1000);
                    if (messageLength > 0) {
                        System.err.printf(
                                "Search job ID: '%s', messages: '%s', getting offset: '%d', length: '%d'\n",
                                searchJobId, messageCount, messageOffset, messageLength);
                        GetMessagesForSearchJobResponse getMessagesForSearchJobResponse =
                                sumoClient.getMessagesForSearchJob(
                                        searchJobId, messageOffset, messageLength);
                        messageOffset += messageLength;
                        List<LogMessage> messages = getMessagesForSearchJobResponse.getMessages();
                        for (LogMessage message : messages) {
                            System.out.println(message.getLogLine());
                        }
                    }
                }

                long endMillis = System.currentTimeMillis();
                long delta = endMillis - startMillis;
                long waitMillis = Math.min(delta, 5000);
                System.err.printf(
                        "Search job ID: '%s', sleeping for: '%d' milliseconds\n",
                        searchJobId, waitMillis);
                Thread.sleep(waitMillis);
            }

            if (getSearchJobStatusResponse.getState().equals("CANCELLED")) {
                System.err.println("Ugh. Search job was cancelled. Exiting...");
                return;
            }

        } catch (Throwable t) {

            // Yikes. We has an error.
            t.printStackTrace(System.err);

        } finally {

            try {
                sumoClient.cancelSearchJob(searchJobId);
            } catch (Throwable t) {
                System.err.printf("Error cancelling search job: '%s'", t.getMessage());
                t.printStackTrace(System.err);
            }
        }
    }
}


