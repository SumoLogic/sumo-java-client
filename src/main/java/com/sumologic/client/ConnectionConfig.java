package com.sumologic.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

public class ConnectionConfig {

    private String protocol;
    private String hostname;
    private int port;
    private Credentials credentials;
    private HttpHost proxy;

    public ConnectionConfig(String protocol, String hostname, int port, Credentials credentials) {
        this(protocol, hostname, port, credentials, null, null, 0);
    }

    public ConnectionConfig(String protocol, String hostname, int port, Credentials credentials, String proxyProtocol, String proxyHost, int proxyPort) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.credentials = credentials;
        if (proxyHost != null) {
            proxy = new HttpHost(proxyHost, proxyPort, proxyProtocol);
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public HttpHost getProxy() { return proxy;}

    public Credentials getCredentials() {
        return credentials;
    }

    public AuthScope getAuthScope() {
        return new AuthScope(hostname, port);
    }

    public UsernamePasswordCredentials getUsernamePasswordCredentials() {
        return new UsernamePasswordCredentials(getCredentials().getAccessId(), getCredentials().getAccessKey());
    }
}
