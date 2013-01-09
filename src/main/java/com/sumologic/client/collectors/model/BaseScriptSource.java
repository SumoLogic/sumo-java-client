package com.sumologic.client.collectors.model;

import com.sumologic.client.util.JacksonUtils;

import java.util.List;

public abstract class BaseScriptSource extends Source {

    private static String COMMANDS = "commands";
    private static String FILE = "file";
    private static String WORKING_DIR = "workingDir";
    private static String TIMEOUT = "timeout";

    /**
     * Returns the list of commands.
     *
     * @return The list of commands.
     */
    public List<String> getCommands() {
        return getProperty(COMMANDS);
    }

    /**
     * Sets the list of commands.
     */
    public void setCommands(List<String> commands) {
        setProperty(COMMANDS, commands);
    }

    /**
     * Returns the script file (if no contents are specified).
     *
     * @return The script file.
     */
    public String getFile() {
        return getProperty(FILE);
    }

    /**
     * Sets the script file.
     */
    public void setFile(String file) {
        setProperty(FILE, file);
    }

    /**
     * Returns the working directory.
     *
     * @return The working directory.
     */
    public String getWorkingDir() {
        return getProperty(WORKING_DIR);
    }

    /**
     * Sets the working directory.
     */
    public void setWorkingDir(String workingDir) {
        setProperty(WORKING_DIR, workingDir);
    }

    /**
     * Returns the script timeout.
     *
     * @return The script timeout.
     */
    public Long getTimeout() {
        return JacksonUtils.asLong(getProperty(TIMEOUT));
    }

    /**
     * Sets the script timeout.
     */
    public void setTimeout(Long timeout) {
        setProperty(TIMEOUT, timeout);
    }
}
