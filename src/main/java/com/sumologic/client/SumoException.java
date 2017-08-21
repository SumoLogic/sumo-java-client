package com.sumologic.client;

/**
 * The base class for all sumo logic API exceptions.
 */
public class SumoException extends RuntimeException {

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
