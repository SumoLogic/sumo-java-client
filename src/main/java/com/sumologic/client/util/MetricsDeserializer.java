package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sumologic.client.metrics.model.CreateMetricsJobResponse;
import com.sumologic.client.metrics.model.Metric;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class MetricsDeserializer extends StdDeserializer<CreateMetricsJobResponse> {

  public MetricsDeserializer() {
    super(CreateMetricsJobResponse.class);
  }

  @Override
  public CreateMetricsJobResponse deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
    CreateMetricsJobResponse metricsResponse = new CreateMetricsJobResponse();
    ObjectCodec codec = parser.getCodec();
    JsonNode root = codec.readTree(parser);

    JsonNode response = root.get("response");
    JsonNode error = root.get("error");
    JsonNode errorMessage = root.get("errorMessage");
    JsonNode results = response.findValue("results");
    JsonNode dimensions = response.findValue("dimensions");

    DateTime[] timestamps = null;
    for(Iterator<JsonNode> iter = results.iterator(); iter.hasNext(); ) {
      JsonNode v = iter.next();
      System.out.println(v);
      if (timestamps == null) {
        timestamps = parseTimestamps(v);
      }
      Metric m = new Metric();
      m.setDimensions(parseDimensions(v));
      m.setTimestamps(timestamps);
      m.setValues(parseValues(v));
      metricsResponse.addMetric(m);
    }

    metricsResponse.setError(error.toString());
    metricsResponse.setErrorMessage(errorMessage.toString());

//    "errorInstanceId"
//    "errorKey"
//    "keyedErrors"
//    "queryInfo":{"startTime":1516898520000,"endTime":1516898640000,"desiredQuantizationInSecs":{"empty":true,"defined":false},"actualQuantizationInSecs":1,"sessionIdStr":"7B6E746B50BC2046"}

    return metricsResponse;
  }

  private DateTime[] parseTimestamps(JsonNode metric) {
    JsonNode timestamp = metric.findValue("timestamp");
    DateTime[] timestamps = new DateTime[timestamp.size()];
    int idx = 0;
    for (Iterator<JsonNode> iter = timestamp.iterator(); iter.hasNext(); idx++) {
      JsonNode v = iter.next();
      timestamps[idx] = new DateTime(v.asLong());
    }
    return timestamps;
  }

  private double[] parseValues(JsonNode metric) {
    JsonNode value = metric.findValue("value");
    double[] values = new double[value.size()];
    int idx =0;
    for(Iterator<JsonNode> iter = value.iterator(); iter.hasNext();idx++) {
      JsonNode v = iter.next();
      values[idx] = v.asDouble();
    }
    return values;
  }

  private HashMap<String, String> parseDimensions(JsonNode metric) {
    JsonNode dim = metric.findValue("dimensions");
    HashMap<String, String> dimensions = new HashMap<>();
    int idx =0;
    for(Iterator<JsonNode> iter = dim.iterator(); iter.hasNext();idx++) {
      JsonNode v = iter.next();
      dimensions.put(v.findValue("key").toString(), v.findValue("value").toString());
    }
    return dimensions;
  }
}