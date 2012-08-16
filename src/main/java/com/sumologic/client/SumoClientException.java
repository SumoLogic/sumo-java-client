package com.sumologic.client;

/**
 * Base class for sumo logic client exceptions.
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public class SumoClientException extends SumoException {

    /**
     * Delegation to Exception ({@see Exception})
     */
    public SumoClientException(String message) {
        super(message);
    }

    /**
     * Delegation to Exception ({@see Exception})
     */
    public SumoClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

