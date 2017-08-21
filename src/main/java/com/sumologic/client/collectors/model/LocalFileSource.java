package com.sumologic.client.collectors.model;

import java.util.List;

/**
 * A source that reads from local files matching a path expression.
 */
public class LocalFileSource extends Source {

    private static String PATH_EXPRESSION = "pathExpression";
    private static String BLACKLIST = "blacklist";

    public LocalFileSource() {
        setSourceType(SourceType.LOCAL_FILE.getType());
    }

    /**
     * Returns the path expression.
     *
     * @return The path expression.
     */
    public String getPathExpression() {
        return getProperty(PATH_EXPRESSION);
    }

    /**
     * Sets the path expression.
     */
    public void setPathExpression(String pathExpression) {
        setProperty(PATH_EXPRESSION, pathExpression);
    }

    /**
     * Returns the blacklist.
     *
     * @return The blacklist.
     */
    public List<String> getBlacklist() {
        return getProperty(BLACKLIST);
    }

    /**
     * Sets the blacklist.
     */
    public void setBlacklist(List<String> blacklist) {
        setProperty(BLACKLIST, blacklist);
    }
}
