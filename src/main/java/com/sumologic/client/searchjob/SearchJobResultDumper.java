/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client.searchjob;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.LogMessage;
import com.sumologic.client.searchjob.model.GetMessagesForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetRecordsForSearchJobResponse;
import com.sumologic.client.searchjob.model.GetSearchJobStatusResponse;
import com.sumologic.client.searchjob.model.SearchJobRecord;
import org.apache.commons.cli.*;

import java.io.*;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A small but useful tool that executes a search job and dumps the results to
 * the standard output. Based on the commandline arguments, the tool will either
 * dump the messages returned by the search job, or the records ("aggregates").
 * In order to reliably dump results for large time ranges, the tool can also
 * operate in incremental mode, splitting a large time range into multiple
 * queries each covering one or more day.
 */
public class SearchJobResultDumper {

    enum OutputFormat {
        CSV,
        JSON
    }

    public static void main(String[] args) throws Exception {

        //
        // Get the commandline arguments.
        //

        // The URL of the Sumo Logic API endpoint. This will usually be
        // https://api.sumologic.com
        String url = null;

        // The credentials of the user for which to execute the query.
        String accessId = null;
        String accessKey = null;

        // The start timestamp and end timestamp for the search job.
        // This can either be an ISO8601 timestamp (without timezone),
        // or milliseconds since the epoch.
        String startTimestamp = null;
        String endTimestamp = null;

        // The timezone to interpret from and to in if given as ISO8601
        String timezone = null;

        String byReceiptTime = null;

        // In chunk mode, the number of hours to execute the search query in.
        long chunkIncrementMillis = -1;

        // The search query.
        String searchQuery = null;

        // The file containing the search query.
        String searchQueryFilename = null;

        // The output format.
        OutputFormat outputFormat = OutputFormat.CSV;

        // Whether to dump aggregates ("records") or not. By default
        // ("false"), the messages returned by the search job will be
        // dumped. Setting this to "true" will instead dump the records.
        boolean dumpAggregates = false;

        // The name of the file to which to write the end timestamp
        // of the last successful run.
        String lastEndFile = null;

        // How many times to retry if the query fails.
        int retry = 1;

        // Create the command line options.
        Options options = createOptions();

        try {

            // Parse the command line.
            CommandLineParser parser = new GnuParser();
            CommandLine commandLine = parser.parse(options, args);

            url = commandLine.getOptionValue("url");
            accessId = commandLine.getOptionValue("accessid");
            accessKey = commandLine.getOptionValue("accesskey");

            if (commandLine.hasOption("catchup")) {

                long thisHour =
                        ((long) Math.floor(System.currentTimeMillis() / 60 / 60 / 1000d))
                                * (60 * 60 * 1000L);
                endTimestamp = Long.toString(thisHour);
                timezone = "UTC";

                if (commandLine.hasOption("catchup-file")) {

                    String catchupFile = commandLine.getOptionValue("catchup-file");
                    try {

                        // Read the timestamp from the file.
                        startTimestamp = readTimestampFromFile(catchupFile);

                    } catch (IOException ioe) {
                        if (!commandLine.hasOption("ignore-missing-catchup-file")) {
                            throw new ParseException(String.format(
                                    "Error reading catchup file: '%s' ('%s')", catchupFile, ioe.getMessage()));
                        } else {
                            System.err.printf(
                                    "Ignoring missing catchup file: '%s' ('%s')\n", catchupFile, ioe.getMessage());
                        }
                    }
                }

                if (startTimestamp == null) {

                    // Figure out the catch-up time range.
                    Long catchupHours = Long.parseLong(commandLine.getOptionValue("catchup"));
                    startTimestamp = Long.toString(thisHour - (catchupHours * 60 * 60 * 1000L));
                }

            } else {

                if (!commandLine.hasOption("from")) {
                    throw new ParseException("-f/--from required if -c/--catchup is omitted");
                }
                startTimestamp = commandLine.getOptionValue("from");

                if (!commandLine.hasOption("to")) {
                    throw new ParseException("-t/--to required if -c/--catchup is omitted");
                }
                endTimestamp = commandLine.getOptionValue("to");

                if (!commandLine.hasOption("tz")) {
                    throw new ParseException("-tz/--timezone required if -c/--catchup is omitted");
                }
                timezone = commandLine.getOptionValue("timezone");
            }

            if (commandLine.hasOption("hours") && commandLine.hasOption("minutes")) {
                throw new ParseException("Please specify only one of --hours and --minutes");
            }

            if (commandLine.hasOption("hours")) {
                chunkIncrementMillis =
                        1000L * 60 * 60 * Long.parseLong(commandLine.getOptionValue("hours"));
            }
            if (commandLine.hasOption("minutes")) {
                chunkIncrementMillis =
                        1000L * 60 * Long.parseLong(commandLine.getOptionValue("minutes"));
            }

            if (commandLine.hasOption("byReceiptTime")) {
                byReceiptTime = commandLine.getOptionValue("byReceiptTime");
            }

            searchQuery = commandLine.getOptionValue("query");
            searchQueryFilename = commandLine.getOptionValue("file");
            if (commandLine.hasOption("json")) {
                outputFormat = OutputFormat.JSON;
            }
            if (commandLine.hasOption("aggregates")) {
                dumpAggregates = true;
            }

            if (searchQuery == null && searchQueryFilename == null) {
                throw new ParseException("Either -q/--query or --file needs to be specified");
            }

            if (commandLine.hasOption("last-end-file")) {
                lastEndFile = commandLine.getOptionValue("last-end-file");
            }

            if (commandLine.hasOption("retry")) {
                String retryValue = commandLine.getOptionValue("retry");
                retry = Integer.parseInt(retryValue);
            }

        } catch (ParseException exp) {
            System.err.println(exp.getMessage());

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("SearchJobResultsDumper", options);
            System.exit(1);
        }


        // Create the Sumo client.
        Credentials credential = new Credentials(accessId, accessKey);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(url);

        // Is the search query a reference to a file?
        if (searchQuery == null && searchQueryFilename != null) {
            searchQuery = readQueryStringFromFile(searchQueryFilename);
        }

        // Translate the timestamps, if necessary from ISO8601.
        long startMillis = getMillis(startTimestamp, timezone);
        long endMillis = getMillis(endTimestamp, timezone);

        // Figure out whether the time range should be covered in a
        // single search job, or if there's an argument telling us
        // to incrementally query in chunks of as many hours as
        // defined by the argument. This last argument is optional.
        long chunkIncrementMillisToUse = endMillis - startMillis;
        if (chunkIncrementMillis != -1) {
            chunkIncrementMillisToUse = chunkIncrementMillis;
        }

        //
        // Main execution.
        //

        // When dumping aggregates/records, we need a CSV writer.
        CSVWriter csvWriter = null;
        AtomicBoolean headerWritten = new AtomicBoolean(false);
        csvWriter = new CSVWriter(new OutputStreamWriter(System.out));

        // For JSON output, we need an object mapper.
        ObjectMapper objectMapper = new ObjectMapper();

        long overallStartTimestamp = System.currentTimeMillis();
        boolean failure = false;
        try {

            do {

                // Figure out the start and end milliseconds since the epoch
                // for the next incremental chunk. If no chunk size was
                // specified, the first chunk is simply the difference
                // between the end timestamp and the start timestamp.
                long chunkStartMillis = startMillis;
                long chunkEndMillis = startMillis + chunkIncrementMillisToUse;

                // The chunk end milliseconds since the epoch should never
                // be further than the actual specified end timestamp.
                chunkEndMillis = Math.min(chunkEndMillis, endMillis);

                // Now we are ready to execute the search job.
                long executionStartMillis = System.currentTimeMillis();
                String prefix = String.format("Chunk from: '%s' to: '%s'",
                        new Date(chunkStartMillis), new Date(chunkEndMillis));
                System.err.printf("--> %s\n", prefix);
                failure = executeSearchJobWithRetry(
                        csvWriter,
                        headerWritten,
                        objectMapper,
                        outputFormat,
                        prefix,
                        sumoClient,
                        searchQuery,
                        dumpAggregates,
                        "" + chunkStartMillis,
                        "" + chunkEndMillis,
                        timezone,
                        retry,
                        lastEndFile,
                        byReceiptTime);
                if (failure) {
                    break;
                }

                long elapsed = System.currentTimeMillis() - executionStartMillis;
                System.err.printf("-----> Done in millis: '%d'\n", elapsed);

                // Set the next start milliseconds since the epoch based
                // on the chunk increment.
                startMillis += chunkIncrementMillisToUse;

            } while (startMillis < endMillis);

            // We are done when the start milliseconds since the epoch
            // has incrementally reached or surpassed the specified
            // end timestamp.

        } finally {

            try {

                // Close the CSV writer, if any.
                csvWriter.close();

            } catch (IOException ioe) {
                System.err.printf("Error closing CSV writer: '%s'", ioe.getMessage());
                ioe.printStackTrace(System.err);
            }
        }

        // Red Rover Red Rover All Over.
        long overallElapsed = System.currentTimeMillis() - overallStartTimestamp;
        System.err.printf("======> Done overall in millis: '%d'\n", overallElapsed);
        if (failure) {
            System.err.println(
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("Finished with errors");
            System.err.println(
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.exit(1);
        }
    }

    private static Options createOptions() {

        Options options = new Options();
        options.addOption(
                OptionBuilder.withLongOpt("url")
                        .withArgName("url")
                        .withDescription("URL of the Sumo Logic API endpoint")
                        .hasArg()
                        .isRequired()
                        .create("u"));
        options.addOption(
                OptionBuilder.withLongOpt("accessid")
                        .withArgName("accessid")
                        .withDescription("Access id of the user to login as")
                        .hasArg()
                        .isRequired()
                        .create("i"));
        options.addOption(
                OptionBuilder.withLongOpt("accesskey")
                        .withArgName("accesskey")
                        .withDescription("Access key of the user to login as")
                        .hasArg()
                        .isRequired()
                        .create("k"));
        options.addOption(
                OptionBuilder.withLongOpt("from")
                        .withArgName("from")
                        .withDescription("Start timestamp in ISO8601 format, or in milliseconds since the epoch")
                        .hasArg()
                        .create("f"));
        options.addOption(
                OptionBuilder.withLongOpt("to")
                        .withArgName("to")
                        .withDescription("End timestamp in ISO8601 format, or in milliseconds since the epoch")
                        .hasArg()
                        .create("t"));
        options.addOption(
                OptionBuilder.withLongOpt("timezone")
                        .withArgName("timezone")
                        .withDescription("The timezone to interpret from and to in")
                        .hasArg()
                        .create("tz"));
        options.addOption(
                OptionBuilder.withLongOpt("byReceiptTime")
                        .withArgName("byReceiptTime")
                        .withDescription("Search by receipt time instead of message time")
                        .hasArg()
                        .create("rt"));
        options.addOption(
                OptionBuilder.withLongOpt("hours")
                        .withArgName("hours")
                        .withDescription("The number of hours to chunk the search query")
                        .hasArg()
                        .create("h"));
        options.addOption(
                OptionBuilder.withLongOpt("minutes")
                        .withArgName("minutes")
                        .withDescription("The number of minutes to chunk the search query")
                        .hasArg()
                        .create("m"));
        options.addOption(
                OptionBuilder.withLongOpt("catchup")
                        .withArgName("catchup")
                        .withDescription("The number of hours to catchup")
                        .hasArg()
                        .create("c"));
        options.addOption(
                OptionBuilder.withLongOpt("catchup-file")
                        .withArgName("catchup-file")
                        .withDescription("The file containing the timestamp from which to catch up from")
                        .hasArg()
                        .create("cf"));
        options.addOption(
                OptionBuilder.withLongOpt("ignore-missing-catchup-file")
                        .withArgName("ignore-missing-catchup-file")
                        .withDescription("If the specified catchup file is missing, ignore and use the --catchup value")
                        .create("imcf"));
        options.addOption(
                OptionBuilder.withLongOpt("query")
                        .withArgName("query")
                        .withDescription("The query to execute")
                        .hasArg()
                        .create("q"));
        options.addOption(
                OptionBuilder.withLongOpt("file")
                        .withArgName("file")
                        .withDescription("The file containing the query to execute")
                        .hasArg()
                        .create());
        options.addOption(
                OptionBuilder.withLongOpt("csv")
                        .withArgName("csv")
                        .withDescription("Format the output as CSV")
                        .create());
        options.addOption(
                OptionBuilder.withLongOpt("aggregates")
                        .withArgName("aggregates")
                        .withDescription("Output the aggregate results, not the messages")
                        .create());
        options.addOption(
                OptionBuilder.withLongOpt("json")
                        .withArgName("json")
                        .withDescription("Format the output as JSON")
                        .create());
        options.addOption(
                OptionBuilder.withLongOpt("last-end-file")
                        .withArgName("last-end-file")
                        .withDescription("Name of the file to write the end timestamp of the last successful run")
                        .hasArg()
                        .create("le"));
        options.addOption(
                OptionBuilder.withLongOpt("retry")
                        .withArgName("retry")
                        .withDescription("Number of times to retry a query in case of an error")
                        .hasArg()
                        .create("r"));
        return options;
    }

    private static String readQueryStringFromFile(String fileName) throws IOException {

        InputStream is = null;
        if (fileName.startsWith("http")) {
            is = new URL(fileName).openStream();
        } else {
            is = new FileInputStream(fileName);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder sb = new StringBuilder(1024);
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static String readTimestampFromFile(String filename) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        try {
            return reader.readLine();
        } finally {
            reader.close();
        }
    }

    private static boolean executeSearchJobWithRetry(CSVWriter csvWriter,
                                                     AtomicBoolean headerWritten,
                                                     ObjectMapper objectMapper,
                                                     OutputFormat outputFormat,
                                                     String prefix,
                                                     SumoLogicClient sumoClient,
                                                     String searchQuery,
                                                     boolean dumpAggregates,
                                                     String startTimestamp,
                                                     String endTimestamp,
                                                     String timeZone,
                                                     int retry,
                                                     String lastEndFile,
                                                     String byReceiptTime) {

        int triesLeft = retry;
        int attempt = 1;
        boolean failure = true;
        while (triesLeft > 0 && failure) {

            // One less try...
            triesLeft--;

            // Execute the search.
            failure = executeSearch(csvWriter,
                    headerWritten,
                    objectMapper,
                    outputFormat,
                    prefix,
                    sumoClient,
                    searchQuery,
                    dumpAggregates,
                    startTimestamp,
                    endTimestamp,
                    timeZone,
                    attempt,
                    lastEndFile,
                    byReceiptTime);

            if (failure) {
                System.err.println(String.format(
                        "Got an error on attempt: '%d', tries left: '%d'", attempt, triesLeft));
            }

            // Increment attempt number...
            attempt++;
        }

        return failure;
    }

    private static boolean executeSearch(CSVWriter csvWriter,
                                         AtomicBoolean headerWritten,
                                         ObjectMapper objectMapper,
                                         OutputFormat outputFormat,
                                         String prefix,
                                         SumoLogicClient sumoClient,
                                         String searchQuery,
                                         boolean dumpAggregates,
                                         String startTimestamp,
                                         String endTimestamp,
                                         String timeZone,
                                         int attempt,
                                         String lastEndFile,
                                         String byReceiptTime) {

        // Create the search job.
        String searchJobId = sumoClient.createSearchJob(
                searchQuery,
                startTimestamp,
                endTimestamp,
                timeZone,
                byReceiptTime);

        System.err.printf("[%s] %s - Search job ID: '%s', attempt: '%d'\n",
                new Date(), prefix, searchJobId, attempt);

        try {

            int messageCount = 0;
            int recordCount = 0;
            int offset = 0;
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
                    System.exit(1);
                }

                // Get any pending warnings.
                List<String> warnings = getSearchJobStatusResponse.getPendingWarnings();
                if (warnings != null && warnings.size() > 0) {
                    System.err.println("WARNINGS:");
                    for (String warning : warnings) {
                        System.err.println(warning);
                    }
                }

                // Get any pending errors.
                List<String> errors = getSearchJobStatusResponse.getPendingErrors();
                if (errors != null && errors.size() > 0) {
                    System.err.println("ERROR:");
                    for (String error : errors) {
                        System.err.println(error);
                    }
                    System.err.flush();
                    return true;
                }

                messageCount = getSearchJobStatusResponse.getMessageCount();
                recordCount = getSearchJobStatusResponse.getRecordCount();
                System.err.printf(
                        "[%s] %s - Search job ID: '%s',  attempt: '%d', messages: '%d', records: '%d'\n",
                        new Date(), prefix, searchJobId, attempt, messageCount, recordCount);

                // Catch up with the raw messages, unless we should just dump aggregates.
                if (!dumpAggregates) {
                    offset = getMessages(
                            csvWriter,
                            headerWritten,
                            objectMapper,
                            outputFormat,
                            prefix,
                            sumoClient,
                            searchJobId,
                            offset,
                            messageCount);
                }

                // Wait if necessary.
                long endMillis = System.currentTimeMillis();
                if (!isDone(getSearchJobStatusResponse)) {
                    gracePeriod(prefix, searchJobId, startMillis, endMillis);
                }
            }

            // If we should dump the aggregate results rather than the raw messages,
            // we will do this here, after the search job has finished computing.
            if (dumpAggregates) {
                getRecords(
                        csvWriter,
                        headerWritten,
                        objectMapper,
                        outputFormat,
                        prefix,
                        sumoClient,
                        searchJobId,
                        0,
                        recordCount);
            }

            if (lastEndFile != null) {

                try {

                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lastEndFile)));
                    writer.write(endTimestamp);
                    writer.close();

                } catch (IOException ioe) {

                    // Yikes. We has an error.
                    System.err.printf("Uncaught exception: '%s'", ioe.getMessage());
                    ioe.printStackTrace(System.err);
                    System.err.flush();
                }
            }

            // No error.
            return false;

        } catch (Throwable t) {

            // Yikes. We has an error.
            System.err.printf("Uncaught exception: '%s'", t.getMessage());
            t.printStackTrace(System.err);
            System.err.flush();

            // Exit with error.
            return true;

        } finally {

            try {
                sumoClient.cancelSearchJob(searchJobId);
            } catch (Throwable t) {
                System.err.printf("Error cancelling search job: '%s'", t.getMessage());
                t.printStackTrace(System.err);
                System.err.flush();
            }
        }
    }

    private static int getMessages(CSVWriter csvWriter,
                                   AtomicBoolean headerWritten,
                                   ObjectMapper objectMapper,
                                   OutputFormat outputFormat,
                                   String prefix,
                                   SumoLogicClient sumoClient,
                                   String searchJobId,
                                   int messageOffset,
                                   int messageCount) {

        int messageLength = 0;
        while ((messageLength = messageCount - messageOffset) > 0) {

            // Did we print the headers already?
            if (outputFormat == OutputFormat.CSV) {
                if (!headerWritten.get()) {

                    // Get the first record so we get the schema.
                    GetMessagesForSearchJobResponse getMessagesForSearchJobResponse =
                            sumoClient.getMessagesForSearchJob(searchJobId, 0, 1);
                    List<LogMessage> messages = getMessagesForSearchJobResponse.getMessages();
                    List<String> fieldNames = new ArrayList<String>(messages.get(0).getFieldNames());
                    Collections.sort(fieldNames);
                    String[] headers = new String[fieldNames.size()];
                    for (int i = 0; i < fieldNames.size(); i++) {
                        String fieldName = fieldNames.get(i);
                        headers[i] = fieldName;
                    }
                    csvWriter.writeNext(headers);
                    headerWritten.set(true);
                }
            }

            messageLength = Math.min(messageLength, 1000);
            if (messageLength > 0) {
                System.err.printf(
                        "[%s] %s - Search job ID: '%s', messages: '%s', getting offset: '%d', length: '%d'\n",
                        new Date(), prefix, searchJobId, messageCount, messageOffset, messageLength);
                System.err.flush();
                GetMessagesForSearchJobResponse getMessagesForSearchJobResponse =
                        sumoClient.getMessagesForSearchJob(
                                searchJobId, messageOffset, messageLength);
                messageOffset += messageLength;

                try {
                    List<LogMessage> messages = getMessagesForSearchJobResponse.getMessages();
                    for (LogMessage message : messages) {
                        Map<String, String> fields = message.getMap();
                        List<String> fieldNames = new ArrayList<String>(message.getFieldNames());
                        Collections.sort(fieldNames);

                        // Write as CSV.
                        if (outputFormat == OutputFormat.CSV) {
                            String[] csv = new String[fields.size()];
                            for (int i = 0; i < fieldNames.size(); i++) {
                                String fieldName = fieldNames.get(i);
                                String fieldValue = fields.get(fieldName);
                                csv[i] = fieldValue;
                            }
                            csvWriter.writeNext(csv);
                        }

                        // Write as JSON.
                        if (outputFormat == OutputFormat.JSON) {
                            String json = objectMapper.writeValueAsString(fields);
                            System.out.println(json);
                        }
                    }
                } catch (IOException ioe) {
                    System.err.printf("Error writing JSON: '%s'", ioe.getMessage());
                    ioe.printStackTrace(System.err);
                }
            }
        }
        return messageOffset;
    }

    private static int getRecords(CSVWriter csvWriter,
                                  AtomicBoolean headerWritten,
                                  ObjectMapper objectMapper,
                                  OutputFormat outputFormat,
                                  String prefix,
                                  SumoLogicClient sumoClient,
                                  String searchJobId,
                                  int recordOffset,
                                  int recordCount) {

        // Did we print the headers already?
        if (outputFormat == OutputFormat.CSV) {
            if (!headerWritten.get()) {

                // Get the first record so we get the schema.
                GetRecordsForSearchJobResponse getRecordsForSearchJobResponse =
                        sumoClient.getRecordsForSearchJob(searchJobId, 0, 1);
                List<SearchJobRecord> records = getRecordsForSearchJobResponse.getRecords();
                List<String> fieldNames = new ArrayList<String>(records.get(0).getFieldNames());
                Collections.sort(fieldNames);
                String[] headers = new String[fieldNames.size()];
                for (int i = 0; i < fieldNames.size(); i++) {
                    String fieldName = fieldNames.get(i);
                    headers[i] = fieldName;
                }
                csvWriter.writeNext(headers);
                headerWritten.set(true);
            }
        }

        int recordLength = 0;
        while ((recordLength = recordCount - recordOffset) > 0) {
            recordLength = Math.min(recordLength, 1000);
            if (recordLength > 0) {
                System.err.printf(
                        "[%s] %s - Search job ID: '%s', records: '%s', getting offset: '%d', length: '%d'\n",
                        new Date(), prefix, searchJobId, recordCount, recordOffset, recordLength);
                System.err.flush();
                GetRecordsForSearchJobResponse getRecordsForSearchJobResponse =
                        sumoClient.getRecordsForSearchJob(
                                searchJobId, recordOffset, recordLength);
                recordOffset += recordLength;
                try {
                    List<SearchJobRecord> records = getRecordsForSearchJobResponse.getRecords();
                    for (SearchJobRecord record : records) {
                        Map<String, String> fields = record.getMap();
                        List<String> fieldNames = new ArrayList<String>(record.getFieldNames());
                        Collections.sort(fieldNames);

                        // Write as CSV.
                        if (outputFormat == OutputFormat.CSV) {
                            String[] csv = new String[fields.size()];
                            for (int i = 0; i < fieldNames.size(); i++) {
                                String fieldName = fieldNames.get(i);
                                String fieldValue = fields.get(fieldName);
                                csv[i] = fieldValue;
                            }
                            csvWriter.writeNext(csv);
                        }

                        // Write as JSON.
                        if (outputFormat == OutputFormat.JSON) {
                            String json = objectMapper.writeValueAsString(fields);
                            System.out.println(json);
                        }
                    }

                } catch (IOException ioe) {
                    System.err.printf("Error writing JSON: '%s'", ioe.getMessage());
                    ioe.printStackTrace(System.err);
                }
            }
        }

        // Flush the CSV writer.
        if (outputFormat == OutputFormat.CSV) {
            try {
                csvWriter.flush();
            } catch (IOException ioe) {
                System.err.printf("Error flushing CSV writer: '%s'", ioe.getMessage());
                ioe.printStackTrace(System.err);
            }
        }

        return recordOffset;
    }

    private static long getMillis(String timestamp, String timezone) {
        if (isLong(timestamp)) {
            return Long.parseLong(timestamp);
        } else {
            return iso8601toMillis(timestamp, timezone);
        }
    }

    private static boolean isLong(String s) {
        try {
            long dummy = Long.parseLong(s);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    private static long iso8601toMillis(String timestamp, String timezone) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .optionalStart()
            .appendLiteral('T')
            .append(DateTimeFormatter.ISO_TIME)
            .toFormatter();

        TimeZone tz = TimeZone.getTimeZone(timezone);
        int offset = tz.getRawOffset();
        if (tz.inDaylightTime(new Date())) {
            offset = offset + tz.getDSTSavings();
        }
        int offsetHours = offset / 1000 / 60 / 60;
        int offsetMinutes = offset / 1000 / 60 % 60;
        String timestampWithTimezone =
                String.format("%s%+03d:%02d", timestamp, offsetHours, offsetMinutes);
        return OffsetDateTime.parse(timestampWithTimezone, formatter).toInstant().toEpochMilli();
    }

    private static boolean isCancelled(GetSearchJobStatusResponse getSearchJobStatusResponse) {
        return getSearchJobStatusResponse.getState().equals("CANCELLED");
    }

    private static boolean isDone(GetSearchJobStatusResponse getSearchJobStatusResponse) {
        return getSearchJobStatusResponse.getState().equals("DONE GATHERING RESULTS");
    }

    private static void gracePeriod(String prefix,
                                    String searchJobId,
                                    long startMillis,
                                    long endMillis)
            throws InterruptedException {
        long maxWaitMillis = 5000;
        long delta = endMillis - startMillis;
        long waitMillis = Math.max(0, Math.min(maxWaitMillis - delta, maxWaitMillis));
        System.err.printf(
                "[%s] %s - Search job ID: '%s', sleeping for: '%d' milliseconds\n",
                new Date(), prefix, searchJobId, waitMillis);
        System.err.flush();
        Thread.sleep(waitMillis);
    }
}


