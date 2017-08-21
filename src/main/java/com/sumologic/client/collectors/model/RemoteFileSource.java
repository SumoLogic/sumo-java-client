package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A source that reads from a remote file over SSH.
 */
public class RemoteFileSource extends Source {

    private static String REMOTE_HOSTS = "remoteHosts";
    private static String REMOTE_PORT = "remotePort";
    private static String REMOTE_USER = "remoteUser";
    private static String REMOTE_PASSWORD = "remotePassword";
    private static String KEY_PATH = "keyPath";
    private static String KEY_PASSWORD = "keyPassword";
    private static String REMOTE_PATH = "remotePath";
    private static String AUTH_METHOD = "authMethod";

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
}
