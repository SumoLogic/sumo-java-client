/**
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
package com.sumologic.client.dashboard;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.dashboard.model.*;
import com.sumologic.client.searchjob.model.SearchJobField;
import com.sumologic.client.searchjob.model.SearchJobRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardExample {

    public static void main(String[] args) throws Exception {

//        String url = "http://localhost:23667";
//        String url = "https://long-api.sumologic.net";
        String url = "https://api.sumologic.com";

        String accessId = read("AccessId");
        String accessKey = read("AccessKey");

        Credentials credential = new Credentials(accessId, accessKey);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(url);

        GetDashboardsResponse getDashboardsResponse = sumoClient.getDashboards(true);
        System.out.println();
        for (Dashboard dashboard : getDashboardsResponse.getDashboards()) {
            String title = String.format("Dashboard: %s (%d)", dashboard.getTitle(), dashboard.getId());
            System.out.println(title);
            printCharacter('=', title.length());
            System.out.println();
            GetDashboardDataResponse getDashboardDataResponse =
                    sumoClient.getDashboardData(dashboard.getId());
            Map<Long, DashboardMonitorData> dashboardMonitorDataById = new HashMap<Long, DashboardMonitorData>();
            for (DashboardMonitorData data : getDashboardDataResponse.getDashboardMonitorDatas()) {
                dashboardMonitorDataById.put(data.getId(), data);
            }
            for (DashboardMonitor dashboardMonitor : dashboard.getDashboardMonitors()) {
                DashboardMonitorData dashboardMonitorData = dashboardMonitorDataById.get(dashboardMonitor.getId());
                title = String.format("Monitor: %s (%d)", dashboardMonitor.getTitle(), dashboardMonitor.getId());
                System.out.println();
                System.out.println(title);
                printCharacter('-', title.length());
                System.out.println();
                System.out.println();
                printTableData(dashboardMonitorData);
            }
            System.out.printf("\n\n");
        }
    }

    private static void printTableData(DashboardMonitorData dashboardMonitorData) {

        List<SearchJobField> fields = dashboardMonitorData.getFields();
        List<SearchJobRecord> records = dashboardMonitorData.getRecords();
        int[] maxColumnSize = new int[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            SearchJobField field = fields.get(i);
            maxColumnSize[i] = Math.max(maxColumnSize[i], field.getName().length());
            for (SearchJobRecord record : records) {
                Map<String, String> recordMap = record.getMap();
                String fieldName = cleanString(field.getName());
                if (recordMap.containsKey(fieldName)) {
                    maxColumnSize[i] = Math.max(maxColumnSize[i], recordMap.get(fieldName).length());
                }
            }
        }

        printTableSeparator(fields, maxColumnSize);

        System.out.print("| ");
        for (int i = 0; i < fields.size(); i++) {
            String formatString = "%-" + maxColumnSize[i] + "s | ";
            String value = cleanString(fields.get(i).getName());
            System.out.printf(formatString, value);
        }
        System.out.println();

        printTableSeparator(fields, maxColumnSize);

        for (int i = 0; i < records.size(); i++) {
            Map<String, String> recordMap = records.get(i).getMap();
            System.out.print("| ");
            for (int j = 0; j < fields.size(); j++) {
                String alignment = "%-";
                if (fields.get(j).getFieldType().equals("long") ||
                        fields.get(j).getFieldType().equals("int")) {
                    alignment = "%";
                }
                String formatString = alignment + maxColumnSize[j] + "s | ";
                String fieldName = fields.get(j).getName();
                String value = " ";
                if (recordMap.containsKey(fieldName)) {
                    value = cleanString(recordMap.get(fieldName));
                }
                System.out.printf(formatString, value);
            }
            System.out.println();
        }

        printTableSeparator(fields, maxColumnSize);
    }

    private static void printCharacter(char c, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
    }

    private static String cleanString(String in) {
        return in.replace("\n", "\\n");
    }

    private static void printTableSeparator(List<SearchJobField> fields, int[] maxColumnSize) {
        System.out.print("+-");
        for (int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < maxColumnSize[i]; j++) {
                System.out.print("-");
            }
            System.out.print("-+");
            if (i < fields.size() - 1) {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    /**
     * A simple method to prompt the user for input, and return the entered input.
     *
     * @param prompt The prompt.
     * @return The user input.
     */
    private static String read(String prompt) {
        System.out.print(prompt + ": ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
            return null;
        }
    }
}


