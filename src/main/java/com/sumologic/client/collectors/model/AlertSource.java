package com.sumologic.client.collectors.model;

/**
 * A source used for alerting on saved searches.
 */
public class AlertSource extends BaseScriptSource {

    public AlertSource() {
        setSourceType(SourceType.ALERT.getType());
    }
}
