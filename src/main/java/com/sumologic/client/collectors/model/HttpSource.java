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

public class HttpSource extends Source {

    public static String ENCODING = "encoding";
    public static String ENDPOINT = "url";
    public static String MESSAGE_PER_REQUEST = "messagePerRequest";

    public HttpSource() {
        setSourceType(SourceType.HTTP.getType());
    }

    /**
     * Returns the encoding.
     *
     * @return The encoding.
     */
    public String getEncoding() {
        return getProperty(ENCODING);
    }

    /**
     * Sets the encoding.
     */
    public void setEncoding(String encoding) {
        setProperty(ENCODING, encoding);
    }

    /**
     * Returns the URL endpoint.
     *
     * @return The URL endpoint.
     */
    public String getEndpoint() {
        return getProperty(ENDPOINT);
    }

    /**
     * Returns whether each request is a single message.
     *
     * @return Whether each request is a single message.
     */
    public Boolean isMessagePerRequest() {
        return getProperty(MESSAGE_PER_REQUEST);
    }

    /**
     * Sets whether each request is a single message.
     */
    public void setMessagePerRequest(Boolean messagePerRequest) {
        setProperty(MESSAGE_PER_REQUEST, messagePerRequest);
    }
}
