//   _____ _____ _____ _____    __    _____ _____ _____ _____
//  |   __|  |  |     |     |  |  |  |     |   __|     |     |
//  |__   |  |  | | | |  |  |  |  |__|  |  |  |  |-   -|   --|
//  |_____|_____|_|_|_|_____|  |_____|_____|_____|_____|_____|
//
//               UNICORNS AT WARP SPEED SINCE 2010

package com.sumologic.client.collectors;

import java.util.List;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.collectors.model.Collector;
import com.sumologic.client.collectors.model.GetCollectorResponse;
import com.sumologic.client.collectors.model.GetCollectorsResponse;
import com.sumologic.client.collectors.model.GetSourcesRequest;
import com.sumologic.client.collectors.model.GetSourcesResponse;
import com.sumologic.client.collectors.model.Source;

public class CollectorAndBladeIdDumper {

  public static void main(String[] args) throws Exception {

    //
    // Get the commandline arguments.
    //

    // The URL of the Sumo Logic API endpoint. This will usually be
    // https://api.sumologic.com
    String url = args[0];

    // The credentials of the user for which to execute the query.
    String email = args[1];
    String password = args[2];
    Credentials credential = new Credentials(email, password);
    SumoLogicClient sumoClient = new SumoLogicClient(credential);
    sumoClient.setURL(url);

    //
    // Get the goods.
    //

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
