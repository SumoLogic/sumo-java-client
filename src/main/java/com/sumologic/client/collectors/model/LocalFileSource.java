package com.sumologic.client.collectors.model;

/**
 * A source that reads from local files matching a path expression.
 *
 * @author Jeffrey Wang
 */
public class LocalFileSource extends BaseFileSource {

    public LocalFileSource() {
        setSourceType(SourceType.LOCAL_FILE.getType());
    }
}
