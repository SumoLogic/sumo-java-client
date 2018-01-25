package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sumologic.client.metrics.CreateMetricsJobResponse;

import java.io.IOException;

public class MetricsDeserializer extends StdDeserializer<CreateMetricsJobResponse> {

  public MetricsDeserializer() {
    super(CreateMetricsJobResponse.class);
  }

  @Override
  public CreateMetricsJobResponse deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
    CreateMetricsJobResponse metricsResponse = new CreateMetricsJobResponse();
    ObjectCodec codec = parser.getCodec();
    JsonNode node = codec.readTree(parser);

    System.out.println(node.toString());

    // try catch block
//    JsonNode colorNode = node.get("color");
//    String color = colorNode.asText();
    metricsResponse.setResponse(node.asText());
    return metricsResponse;
  }
}