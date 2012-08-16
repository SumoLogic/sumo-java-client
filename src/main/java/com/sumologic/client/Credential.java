package com.sumologic.client;

/**
 * The sumo credentials to access the web service.
 *
 * @author Daphne Hsieh
 * @author Sebastian Mies
 * @version 1.0
 */
public class Credential {

    /**
     * Constructs the credentials using email and password.
     *
     * @param email Your email
     * @param password Your password
     */
    public Credential(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the HTTP auth user name
     *
     * @return The HTTP auth user name
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the HTTP auth password
     *
     * @return The HTTP auth password
     */
    public String getPassword() {
        return this.password;
    }

    private String email;
    private String password;
}
