package com.sumologic.client.model;

public abstract class SumoEntity {

    private String eTag;

    /**
     * Gets the ETag.
     *
     * @return The ETag.
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the ETag. Used for optimistic locking; if an invalid ETag is provided, update
     * operations will fail, so only modify this if you know what you're doing.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
