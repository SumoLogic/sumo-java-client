package com.sumologic.client.metrics;

import org.apache.http.HttpResponse;
import com.sumologic.client.util.*;
import java.io.IOException;
import java.io.InputStream;

public final class CreateMetricsJobResponse {

  private String id;
  private String response;

  /**
   * Returns the ID of the metrics job.
   *
   * @return The ID of the metrics job.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the ID of the metrics job.
   *
   * @param id The ID of the metrics job.
   */
  public void setId(String id) {
    this.id = id;
  }

  public String getResponse() {
    return response;
  }
  public void setResponse(String response) {
    this.response = response;
  }

}

