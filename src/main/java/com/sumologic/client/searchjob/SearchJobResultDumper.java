package com.sumologic.client.searchjob;

import java.util.List;

import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchjob.model.GetMessagesForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetSearchJobStatusResponse;
import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;

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
                    (!isDone(getSearchJobStatusResponse) &&
                            !isCancelled(getSearchJobStatusResponse))) {
                long startMillis = System.currentTimeMillis();

                // Get the job status and the latest counts from the status.
                getSearchJobStatusResponse = sumoClient.getSearchJobStatus(searchJobId);
                if (isCancelled(getSearchJobStatusResponse)) {
                    System.err.println("Ugh. Search job was cancelled. Exiting...");
                    System.err.flush();
                    return;
                }
                messageCount = getSearchJobStatusResponse.getMessageCount();
                recordCount = getSearchJobStatusResponse.getRecordCount();
                System.err.printf(
                        "Search job ID: '%s', messages: '%d', records: '%d'\n",
                        searchJobId, messageCount, recordCount);

                // Catch up with the messages.
                messageOffset = getMessages(sumoClient, searchJobId, messageCount, messageOffset);

                // Wait if necessary.
                long endMillis = System.currentTimeMillis();
                if (!isDone(getSearchJobStatusResponse)) {
                    gracePeriod(searchJobId, startMillis, endMillis);
                }
            }
        } catch (Throwable t) {

            // Yikes. We has an error.
            t.printStackTrace(System.err);
            System.err.flush();

        } finally {

            try {
                sumoClient.cancelSearchJob(searchJobId);
            } catch (Throwable t) {
                System.err.printf("Error cancelling search job: '%s'", t.getMessage());
                t.printStackTrace(System.err);
            }
        }
    }

    private static boolean isCancelled(GetSearchJobStatusResponse getSearchJobStatusResponse) {
        return getSearchJobStatusResponse.getState().equals("CANCELLED");
    }

    private static boolean isDone(GetSearchJobStatusResponse getSearchJobStatusResponse) {
        return getSearchJobStatusResponse.getState().equals("DONE GATHERING RESULTS");
    }

    private static int getMessages(SumoLogicClient sumoClient,
                                   String searchJobId,
                                   int messageCount,
                                   int messageOffset) {
        int messageLength = 0;
        while ((messageLength = messageCount - messageOffset) > 0) {
            messageLength = Math.min(messageLength, 1000);
            if (messageLength > 0) {
                System.err.printf(
                        "Search job ID: '%s', messages: '%s', getting offset: '%d', length: '%d'\n",
                        searchJobId, messageCount, messageOffset, messageLength);
                System.err.flush();
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
        return messageOffset;
    }

    private static void gracePeriod(String searchJobId, long startMillis, long endMillis)
            throws InterruptedException {
        long maxWaitMillis = 5000;
        long delta = endMillis - startMillis;
        long waitMillis = Math.max(0, Math.min(maxWaitMillis - delta, maxWaitMillis));
        System.err.printf(
                "Search job ID: '%s', sleeping for: '%d' milliseconds\n",
                searchJobId, waitMillis);
        System.err.flush();
        Thread.sleep(waitMillis);
    }
}


