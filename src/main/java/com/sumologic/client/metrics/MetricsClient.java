package com.sumologic.client.metrics;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;
import org.apache.http.HttpStatus;

public class MetricsClient {
  private HttpUtils httpUtils;

  public MetricsClient(HttpUtils httpUtils) {
    this.httpUtils = httpUtils;
  }

  public CreateMetricsJobResponse createMetricsJob(
          ConnectionConfig connection,
          CreateMetricsJobRequest createMetricsJobRequest) {

    String uri = UrlParameters.METRICS_JOBS_SERVICE;

    return httpUtils.post(
            connection,
            uri,
            createMetricsJobRequest,
            HttpUtils.toRequestHeaders(
                    "Content-type", "application/json",
                    "Accept", "application/json"),
            new DeserializingResponseHandler<CreateMetricsJobRequest,
                    CreateMetricsJobResponse>(CreateMetricsJobResponse.class),
            HttpStatus.SC_OK);
  }

}
