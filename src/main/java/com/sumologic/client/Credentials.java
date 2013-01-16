package com.sumologic.client;

/**
 * The sumo credentials to access the web service.
 *
 * @author Daphne Hsieh
 * @author Sebastian Mies
 */
public class Credentials {
    private String email;
    private String password;

    /**
     * Constructs the credentials using email and password.
     *
     * @param email Your email
     * @param password Your password
     */
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the user name
     *
     * @return The user name
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the password
     *
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }


}
