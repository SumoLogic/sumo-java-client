package com.sumologic.client.model;

import com.sumologic.client.Headers;

/**
 * The search order which is either ascending or descending (default)
 *
 * @author Sebastian Mies
 * @version 1.0
 */
public enum SearchOrder {
    ASCENDING(Headers.SEARCH_ORDER_ASC),
    DESCENDING(Headers.SEARCH_ORDER_DESC),
    DEFAULT(Headers.SEARCH_ORDER_DESC);

    /**
     * Order of the of the search results.
     *
     * @param order May be either Headers.SEARCH_ORDER_DESC
     *              or Headers.SEARCH_ORDER_ASC
     * @see Headers
     */
    SearchOrder(String order) {
        assert(order.equals(Headers.SEARCH_ORDER_DESC) || order.equals(Headers.SEARCH_ORDER_ASC));
        this.order = order;
    }

    /**
     * Returns the order parameter value string for the HTTP query.
     *
     * @return The order parameter value string for the HTTP query.
     */
    String parameterValue() {
        return order;
    }

    private String order;
}
