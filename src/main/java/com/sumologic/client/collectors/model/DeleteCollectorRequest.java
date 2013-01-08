package com.sumologic.client.collectors.model;

import com.sumologic.client.model.HttpDeleteRequest;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

/**
 * A request to delete a collector from the Sumo Logic system.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class DeleteCollectorRequest implements HttpDeleteRequest {

    private Long id;

    public DeleteCollectorRequest(Long id) {
        this.id = id;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }
}
