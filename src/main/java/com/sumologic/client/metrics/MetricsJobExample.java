package com.sumologic.client.metrics;

import com.sumologic.client.Credentials;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.metrics.model.CreateMetricsJobResponse;
import com.sumologic.client.metrics.model.Metric;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MetricsJobExample {


  public static void main(String[] args) throws Exception {

    // The API URL should always point to
    //
    //        https://api.sumologic.com
    //
    // unless you are developing on the Sumo
    // service itself, in which case
    //
    //        http://localhost:23667
    //
    // is a good choice as well.
//    String url = "http://localhost:23667";
//        String url = "https://api.sumologic.com";
//        String url = "https://long-api.sumologic.net";
    String url = "https://long-api.sumologic.net/api/v1";
    // Read the accessId and the accessKey from
    // the commandline so we don't have to
    // hard-code credentials in this example.
    String accessId = read("AccessId");
    String accessKey = read("AccessKey");

    // With url, accessId, and accessKey in hand,
    // we can now construct a Sumo Logic API
    // client instance.
    Credentials credential = new Credentials(accessId, accessKey);
    SumoLogicClient sumoClient = new SumoLogicClient(credential);
    sumoClient.setURL(url);

    // There's some options in creating a metrics
    // job. But on the high level, we need to
    // specify a query, a time range, and a
    // time zone.
    CreateMetricsJobResponse metricsJob = sumoClient.createMetricsJob(
            "_sourceCategory=cqmerger metric=CPU_Idle",  // This query will return all messages
            "2018-01-25T08:42:00",    // between this start time and
            "2018-01-25T08:44:00",    // this end time, specified in ISO 8601 format
            "america/los_angeles");   // and assuming we are in California.

    // If successful, we will have gotten back
    // a json with all time series. We print the meta data

    for (Metric m : metricsJob) {
      System.out.println(m.getDimensions());
    }
    System.out.println(metricsJob.getStartTime());
    System.out.println(metricsJob.getEndTime());
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



