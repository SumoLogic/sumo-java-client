/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.client.collectors.model;

import com.sumologic.client.util.JacksonUtils;

import java.util.List;

public abstract class BaseScriptSource extends Source {

    private static final String COMMANDS = "commands";
    private static final String FILE = "file";
    private static final String WORKING_DIR = "workingDir";
    private static final String TIMEOUT = "timeout";

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
