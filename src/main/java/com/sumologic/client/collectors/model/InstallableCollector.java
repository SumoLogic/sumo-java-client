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
 * An installable collector in the Sumo Logic system.
 */
public class InstallableCollector extends Collector {

    private String osVersion;
    private String osName;
    private String osArch;
    private Long upTime;

    /**
     * Returns the OS version.
     *
     * @return The OS version.
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Returns the OS name.
     *
     * @return The OS name.
     */
    public String getOsName() {
        return osName;
    }

    /**
     * Returns the OS architecture.
     *
     * @return The OS architecture.
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     * Returns the collector's uptime.
     *
     * @return The collector's uptime.
     */
    public Long getUpTime() {
        return upTime;
    }
}
