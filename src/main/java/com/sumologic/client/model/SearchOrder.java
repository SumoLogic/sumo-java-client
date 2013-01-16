package com.sumologic.client.model;

import com.sumologic.client.UrlParameters;

/**
 * The search order which is either ascending or descending (default)
 *
 * @author Sebastian Mies
 */
public enum SearchOrder {
    ASCENDING(UrlParameters.SEARCH_ORDER_ASC),
    DESCENDING(UrlParameters.SEARCH_ORDER_DESC),
    DEFAULT(UrlParameters.SEARCH_ORDER_DESC);

    private String order;

    /**
     * Order of the of the search results.
     *
     * @param order May be either Headers.SEARCH_ORDER_DESC
     *              or Headers.SEARCH_ORDER_ASC
     * @see com.sumologic.client.UrlParameters
     */
    SearchOrder(String order) {
        assert(order.equals(UrlParameters.SEARCH_ORDER_DESC) || order.equals(UrlParameters.SEARCH_ORDER_ASC));
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

}
