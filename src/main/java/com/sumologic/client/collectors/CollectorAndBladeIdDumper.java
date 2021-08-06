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
package com.sumologic.client.collectors;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.collectors.model.*;

import java.util.List;

public class CollectorAndBladeIdDumper {

    public static void main(String[] args) throws Exception {

        // Get the commandline arguments.
        // The URL of the Sumo Logic API endpoint. This will usually be
        // https://api.sumologic.com
        String url = args[0];

        // The credentials of the user for which to execute the query.
        String accessId = args[1];
        String accessKey = args[2];
        Credentials credential = new Credentials(accessId, accessKey);
        SumoLogicClient sumoClient = new SumoLogicClient(credential);
        sumoClient.setURL(url);

        GetCollectorsResponse getCollectorResponse = sumoClient.getCollectors();
        List<Collector> collectors = getCollectorResponse.getCollectors();
        for (Collector collector : collectors) {
            System.err.printf("Collector ID: '%016X', name: '%s'\n",
                    collector.getId(), collector.getName());
            long offset = 0;
            long increment = 50;
            boolean done = false;
            while (!done) {
                GetSourcesRequest getSourcesRequest = new GetSourcesRequest(collector.getId());
                getSourcesRequest.setOffset(offset);
                getSourcesRequest.setLimit(increment);
                GetSourcesResponse getSourcesResponse = sumoClient.getSources(getSourcesRequest);
                List<Source> sources = getSourcesResponse.getSources();
                for (Source source : sources) {
                    System.err.printf("   Source ID: '%016X', name: '%s'\n",
                            source.getId(), source.getName());
                }
                offset += increment;
                if (sources.size() < increment) {
                    done = true;
                }
            }
        }
    }
}
