package com.sumologic.client.collectors.model;

/**
 * A source that executes a script and collects its output.
 *
 * @author Jeffrey Wang
 * @version 1.1
 */
public class ScriptSource extends BaseScriptSource {

    private static String SCRIPT = "script";
    private static String CRON_EXPRESSION = "cronExpression";

    public ScriptSource() {
        setSourceType(SourceType.SCRIPT.getType());
    }

    /**
     * Returns the script contents (if no file is specified).
     *
     * @return The script contents.
     */
    public String getScript() {
        return getProperty(SCRIPT);
    }

    /**
     * Sets the script contents.
     */
    public void setScript(String script) {
        setProperty(SCRIPT, script);
    }

    /**
     * Returns the cron expression.
     *
     * @return The cron expression.
     */
    public String getCronExpression() {
        return getProperty(CRON_EXPRESSION);
    }

    /**
     * Sets the cron expression.
     */
    public void setCronExpression(String cronExpression) {
        setProperty(CRON_EXPRESSION, cronExpression);
    }
}
