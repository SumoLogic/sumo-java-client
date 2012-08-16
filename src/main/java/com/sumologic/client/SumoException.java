package com.sumologic.client;

/**
 * The base class for all sumo logic API exceptions.
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public class SumoException extends Exception {

    /**
     * Delegation to Exception ({@see Exception})
     */
    public SumoException() {
        super();
    }

    /**
     * Delegation to Exception ({@see Exception})
     */
    public SumoException(String message) {
        super(message);
    }

    /**
     * Delegation to Exception ({@see Exception})
     */
    public SumoException(String message, Throwable cause) {
        super(message, cause);
    }

}
