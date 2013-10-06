package com.sumologic.client.dashboard;

import org.apache.http.HttpStatus;

import com.sumologic.client.ConnectionConfig;
import com.sumologic.client.UrlParameters;
import com.sumologic.client.dashboard.model.GetDashboardDataRequest;
import com.sumologic.client.dashboard.model.GetDashboardDataResponse;
import com.sumologic.client.dashboard.model.GetDashboardRequest;
import com.sumologic.client.dashboard.model.GetDashboardResponse;
import com.sumologic.client.dashboard.model.GetDashboardsRequest;
import com.sumologic.client.dashboard.model.GetDashboardsResponse;
import com.sumologic.client.util.DeserializingResponseHandler;
import com.sumologic.client.util.HttpUtils;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class DashboardClient {

    // Implementation.

    private HttpUtils httpUtils;

    public DashboardClient(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public GetDashboardsResponse getDashboards(
            ConnectionConfig connection,
            GetDashboardsRequest getDashboardsRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE;
        return httpUtils.get(
                connection,
                uri,
                getDashboardsRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardsRequest, GetDashboardsResponse>(
                        GetDashboardsResponse.class),
                HttpStatus.SC_OK);
    }

    public GetDashboardResponse getDashboard(
            ConnectionConfig connection,
            GetDashboardRequest getDashboardRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE +
                "/" + getDashboardRequest.getId();
        return httpUtils.get(
                connection,
                uri,
                getDashboardRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardRequest, GetDashboardResponse>(
                        GetDashboardResponse.class),
                HttpStatus.SC_OK);
    }

    public GetDashboardDataResponse getDashboardData(
            ConnectionConfig connection,
            GetDashboardDataRequest getDashboardDataRequest) {

        String uri = UrlParameters.DASHBOARDS_SERVICE +
                "/" + getDashboardDataRequest.getId() +
                "/" + UrlParameters.DASHBOARDS_SERVICE_DATA;
        return httpUtils.get(
                connection,
                uri,
                getDashboardDataRequest,
                HttpUtils.toRequestHeaders(
                        "Accept", "application/json"),
                new DeserializingResponseHandler<GetDashboardDataRequest, GetDashboardDataResponse>(
                        GetDashboardDataResponse.class),
                HttpStatus.SC_OK);
    }
}
