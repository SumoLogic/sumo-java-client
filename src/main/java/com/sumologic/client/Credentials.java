package com.sumologic.client;

/**
 * The sumo credentials to access the web service.
 *
 * @author Daphne Hsieh
 * @author Sebastian Mies
 */
public class Credentials {
    private String accessId;
    private String accessKey;

    /**
     * Constructs the credentials using accessId and accessKey.
     *
     * @param accessId Your access id
     * @param accessKey Your access key
     */
    public Credentials(String accessId, String accessKey) {
        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    /**
     * Returns the user name
     *
     * @return The user name
     *
     * @deprecated Replaced by {@link #getAccessId()}
     */
    @Deprecated
    public String getEmail() {
        return getAccessId();
    }

    /**
     * Returns the password
     *
     * @return The password
     *
     * @deprecated Replaced by {@link #getAccessKey()}
     */
    @Deprecated
    public String getPassword() {
        return getAccessKey();
    }

    /**
     * Returns the access id
     *
     * @return The access id
     */
    public String getAccessId() {
        return this.accessId;
    }

    /**
     * Returns the access key
     *
     * @return The access key
     */
    public String getAccessKey() {
        return this.accessKey;
    }



}
