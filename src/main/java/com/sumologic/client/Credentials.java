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
package com.sumologic.client;

/**
 * The sumo credentials to access the web service.
 */
public class Credentials {
    private String accessId;
    private String accessKey;

    /**
     * Constructs the credentials using accessId and accessKey.
     *
     * @param accessId Your access id
     * @param accessKey Your access key
     */
    public Credentials(String accessId, String accessKey) {
        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    /**
     * Returns the user name
     *
     * @return The user name
     *
     * @deprecated Replaced by {@link #getAccessId()}
     */
    @Deprecated
    public String getEmail() {
        return getAccessId();
    }

    /**
     * Returns the password
     *
     * @return The password
     *
     * @deprecated Replaced by {@link #getAccessKey()}
     */
    @Deprecated
    public String getPassword() {
        return getAccessKey();
    }

    /**
     * Returns the access id
     *
     * @return The access id
     */
    public String getAccessId() {
        return this.accessId;
    }

    /**
     * Returns the access key
     *
     * @return The access key
     */
    public String getAccessKey() {
        return this.accessKey;
    }


}
