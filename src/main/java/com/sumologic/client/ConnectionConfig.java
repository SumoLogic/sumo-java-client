/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

/**
 * Connection configuration for the client.
 */
public class ConnectionConfig {

    private final String protocol;
    private final String hostname;
    private final int port;
    private final Credentials credentials;
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

    public HttpHost getProxy() {
        return proxy;
    }

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
