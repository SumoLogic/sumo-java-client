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
 * A source that reads from a remote file over SSH.
 */
public class RemoteFileSource extends Source {

    private static final String REMOTE_HOSTS = "remoteHosts";
    private static final String REMOTE_PORT = "remotePort";
    private static final String REMOTE_USER = "remoteUser";
    private static final String REMOTE_PASSWORD = "remotePassword";
    private static final String KEY_PATH = "keyPath";
    private static final String KEY_PASSWORD = "keyPassword";
    private static final String REMOTE_PATH = "remotePath";
    private static final String AUTH_METHOD = "authMethod";
    private static final String DENYLIST = "denylist";

    public RemoteFileSource() {
        setSourceType(SourceType.REMOTE_FILE.getType());
    }

    /**
     * Returns the remote host.
     *
     * @return The remote host.
     */
    public List<String> getRemoteHosts() {
        return getProperty(REMOTE_HOSTS);
    }

    /**
     * Sets the remote host.
     */
    public void setRemoteHosts(List<String> remoteHosts) {
        setProperty(REMOTE_HOSTS, remoteHosts);
    }

    /**
     * Returns the remote port.
     *
     * @return The remote port.
     */
    public Integer getRemotePort() {
        return getProperty(REMOTE_PORT);
    }

    /**
     * Sets the remote port.
     */
    public void setRemotePort(Integer remotePort) {
        setProperty(REMOTE_PORT, remotePort);
    }

    /**
     * Returns the remote user.
     *
     * @return The remote user.
     */
    public String getRemoteUser() {
        return getProperty(REMOTE_USER);
    }

    /**
     * Sets the remote user.
     */
    public void setRemoteUser(String remoteUser) {
        setProperty(REMOTE_USER, remoteUser);
    }

    /**
     * Returns the remote password (hidden in server response).
     *
     * @return The remote password.
     */
    public String getRemotePassword() {
        return getProperty(REMOTE_PASSWORD);
    }

    /**
     * Sets the remote password.
     */
    public void setRemotePassword(String remotePassword) {
        setProperty(REMOTE_PASSWORD, remotePassword);
    }

    /**
     * Returns the remote path.
     *
     * @return The remote path.
     */
    public String getKeyPath() {
        return getProperty(KEY_PATH);
    }

    /**
     * Sets the key path.
     */
    public void setKeyPath(String keyPath) {
        setProperty(KEY_PATH, keyPath);
    }

    /**
     * Returns the key password (hidden in server response).
     *
     * @return The key password.
     */
    public String getKeyPassword() {
        return getProperty(KEY_PASSWORD);
    }

    /**
     * Sets the key password.
     */
    public void setKeyPassword(String keyPassword) {
        setProperty(KEY_PASSWORD, keyPassword);
    }

    /**
     * Returns the remote file path.
     *
     * @return The remote file path.
     */
    public String getRemotePath() {
        return getProperty(REMOTE_PATH);
    }

    /**
     * Sets the remote file path.
     */
    public void setRemotePath(String remotePath) {
        setProperty(REMOTE_PATH, remotePath);
    }

    /**
     * Returns the auth method.
     *
     * @return The auth method.
     */
    public String getAuthMethod() {
        return getProperty(AUTH_METHOD);
    }

    /**
     * Sets the auth method.
     */
    public void setAuthMethod(String authMethod) {
        setProperty(AUTH_METHOD, authMethod);
    }

    /**
     * Returns the denylist.
     *
     * @return The denylist.
     */
    public List<String> getDenylist() {
        return getProperty(DENYLIST);
    }

    /**
     * Sets the denylist.
     */
    public void setDenylist(List<String> denylist) {
        setProperty(DENYLIST, denylist);
    }
}
