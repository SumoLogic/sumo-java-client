package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sumologic.client.metrics.CreateMetricsJobRequest;

import java.io.IOException;

public class MetricsJobSerializer extends StdSerializer<CreateMetricsJobRequest> {

  public MetricsJobSerializer() {
    super(CreateMetricsJobRequest.class, true);
  }

  @Override
  public void serialize(CreateMetricsJobRequest metricsJobRequest, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeArrayFieldStart("query");
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("query", metricsJobRequest.getQuery());
    jsonGenerator.writeStringField("rowId", metricsJobRequest.getRowId());
    jsonGenerator.writeEndObject();
    jsonGenerator.writeEndArray();
    jsonGenerator.writeStringField("startTime", metricsJobRequest.getFrom());
    jsonGenerator.writeStringField("endTime", metricsJobRequest.getTo());
    jsonGenerator.writeStringField("requestedDataPoints", Integer.toString(metricsJobRequest.getRequestedDataPoints()));
    jsonGenerator.writeStringField("maxDataPoints", Integer.toString(metricsJobRequest.getMaxDataPoints()));
    jsonGenerator.writeEndObject();
  }
}
