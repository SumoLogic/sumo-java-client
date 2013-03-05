package com.sumologic.client.collectors.model;

/**
 * A source used for alerting on saved searches.
 *
 * @author Jeffrey Wang
 */
public class AlertSource extends BaseScriptSource {

    public AlertSource() {
        setSourceType(SourceType.ALERT.getType());
    }
}
