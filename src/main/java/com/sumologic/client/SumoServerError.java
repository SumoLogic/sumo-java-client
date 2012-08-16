package com.sumologic.client;

/**
 * The sumo API error interface for an extensible enum pattern.
 *
 * @author Sebastian Mies
 * @version 1.0
 * @see SumoSearchErrors
 * @see SumoServerErrors
 */
public interface SumoServerError {
    /**
     * Returns the identifier of the error., e.g.,
     * "unknown.timezone" when the timezone is unknown
     *
     * @return The identifier of the error
     */
    public String getId();
}
