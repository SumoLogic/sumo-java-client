package com.sumologic.client.collectors.model;

/**
 * A source that reads from local files matching a path expression.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class LocalFileSource extends Source {

    private static String PATH_EXPRESSION = "pathExpression";
    private static String BLACKLIST = "blacklist";

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
    public String getBlacklist() {
        return getProperty(BLACKLIST);
    }

    /**
     * Sets the blacklist.
     */
    public void setBlacklist(String blacklist) {
        setProperty(BLACKLIST, blacklist);
    }
}
