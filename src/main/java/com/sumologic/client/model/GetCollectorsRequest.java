package com.sumologic.client.model;

/**
 * A request to get collectors from the sumo logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class GetCollectorsRequest {

    private Long id;

    /**
     * Returns the id.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the id.
     *
     * @return This object.
     */
    public GetCollectorsRequest withId(Long id) {
        setId(id);
        return this;
    }

    @Override
    public String toString() {
        return "";
    }
}
