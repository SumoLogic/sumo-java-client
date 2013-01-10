package com.sumologic.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

public class AuthContext {

    private String protocol;
    private String hostname;
    private int port;
    private Credentials credentials;

    public AuthContext(String protocol, String hostname, int port, Credentials credentials) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.credentials = credentials;
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

    public Credentials getCredentials() {
        return credentials;
    }

    public AuthScope getAuthScope() {
        return new AuthScope(hostname, port);
    }

    public UsernamePasswordCredentials getUsernamePasswordCredentials() {
        return new UsernamePasswordCredentials(credentials.getEmail(), credentials.getPassword());
    }
}
