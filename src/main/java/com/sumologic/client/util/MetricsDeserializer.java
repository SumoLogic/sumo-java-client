package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sumologic.client.metrics.CreateMetricsJobResponse;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    JsonNode datapoints = response.findValue("datapoints");
    JsonNode timestamp = datapoints.findValue("timestamp");
    JsonNode value = datapoints.findValue("value");
    JsonNode error = root.get("error");
    JsonNode errorMessage = root.get("errorMessage");

    metricsResponse.setError(error.toString());
    metricsResponse.setErrorMessage(errorMessage.toString());

    DateTime timestamps[] = new DateTime[timestamp.size()];
    int idx = 0;
    for (Iterator<JsonNode> iter = timestamp.iterator(); iter.hasNext(); idx++) {
      JsonNode v = iter.next();
      timestamps[idx] = new DateTime(v.asLong());
    }
    metricsResponse.setTimestamps(timestamps);

    double values[] = new double[timestamp.size()];
    idx = 0;
    for (Iterator<JsonNode> iter = value.iterator(); iter.hasNext(); idx++) {
      JsonNode v = iter.next();
      values[idx] = v.asDouble();
    }
    metricsResponse.setValues(values);


//    for (el : response.elements()) {
//
//    }

//    JsonNode datapoints = root.get("datapoints");

    // [{"stringValue":"a","intValue":1,"booleanValue":true},
    // {"stringValue":"bc","intValue":3,"booleanValue":false}]


//    "error"
//    "errorMessage"
//    "errorInstanceId"
//    "errorKey"
//    "keyedErrors"

//    "queryInfo":{"startTime":1516898520000,"endTime":1516898640000,"desiredQuantizationInSecs":{"empty":true,"defined":false},"actualQuantizationInSecs":1,"sessionIdStr":"7B6E746B50BC2046"}

    metricsResponse.setResponse(datapoints.toString());
    return metricsResponse;
  }
}