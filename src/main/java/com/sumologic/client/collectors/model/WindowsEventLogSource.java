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

import java.util.List;

/**
 * A source that reads from local Windows event logs.
 */
public class WindowsEventLogSource extends Source {

    private static final String LOG_NAMES = "logNames";

    public WindowsEventLogSource() {
        setSourceType(SourceType.LOCAL_WINDOWS_EVENT_LOG.getType());
    }

    /**
     * Returns the list of event types (empty if includeAll = true).
     *
     * @return The list of event types.
     */
    public List<String> getLogNames() {
        return getProperty(LOG_NAMES);
    }

    /**
     * Sets the list of event types.
     */
    public void setLogNames(List<String> logNames) {
        setProperty(LOG_NAMES, logNames);
    }
}
