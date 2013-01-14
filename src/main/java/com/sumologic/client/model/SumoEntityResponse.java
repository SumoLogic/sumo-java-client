package com.sumologic.client.model;

public abstract class SumoEntityResponse {

    protected abstract SumoEntity getEntity();

    public void setETag(String eTag) {
        getEntity().setETag(eTag);
    }
}
