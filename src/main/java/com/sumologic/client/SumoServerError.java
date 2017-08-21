package com.sumologic.client;

/**
 * The sumo API error interface for an extensible enum pattern.
 *
 * @see com.sumologic.client.collectors.SumoCollectorErrors
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
