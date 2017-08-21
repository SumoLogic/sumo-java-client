/**
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

/**
 * A source that executes a script and collects its output.
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
