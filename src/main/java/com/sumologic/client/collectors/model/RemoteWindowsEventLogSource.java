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
package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A source that reads from remote Windows event logs.
 */
public class RemoteWindowsEventLogSource extends WindowsEventLogSource {

    private static final String DOMAIN = "domain";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String HOSTS = "hosts";

    public RemoteWindowsEventLogSource() {
        setSourceType(SourceType.REMOTE_WINDOWS_EVENT_LOG.getType());
    }

    /**
     * Returns the Windows domain.
     *
     * @return The Windows domain.
     */
    public String getDomain() {
        return getProperty(DOMAIN);
    }

    /**
     * Sets the Windows domain.
     */
    public void setDomain(String domain) {
        setProperty(DOMAIN, domain);
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return getProperty(USERNAME);
    }

    /**
     * Sets the username.
     */
    public void setUsername(String username) {
        setProperty(USERNAME, username);
    }

    /**
     * Returns the password (hidden in server response).
     *
     * @return The password.
     */
    public String getPassword() {
        return getProperty(PASSWORD);
    }

    /**
     * Sets the password.
     */
    public void setPassword(String password) {
        setProperty(PASSWORD, password);
    }

    /**
     * Returns the list of remote Windows hosts.

     * @return The list of remote Windows hosts.
     */
    public List<String> getHosts() {
        return getProperty(HOSTS);
    }

    /**
     * Sets the list of remote Windows hosts.
     */
    public void setHosts(List<String> hosts) {
        setProperty(HOSTS, hosts);
    }

}
