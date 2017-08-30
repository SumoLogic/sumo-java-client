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
package com.sumologic.client.searchjob.model;

import com.sumologic.client.model.LogMessage;

import java.util.List;

public class GetMessagesForSearchJobResponse {

    private List<SearchJobField> fields;
    private List<LogMessage> messages;

    /**
     * Returns the fields.
     *
     * @return The fields.
     */
    public List<SearchJobField> getFields() {
        return fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     */
    public void setFields(List<SearchJobField> fields) {
        this.fields = fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields The fields.
     * @return This object.
     */
    public GetMessagesForSearchJobResponse withFields(List<SearchJobField> fields) {
        setFields(fields);
        return this;
    }

    /**
     * Get the messages.
     *
     * @return The messages.
     */
    public List<LogMessage> getMessages() {
        return messages;
    }

    /**
     * Sets the messages.
     *
     * @param messages The messages.
     */
    public void setMessages(List<LogMessage> messages) {
        this.messages = messages;
    }

    /**
     * Sets the messages
     *
     * @param messages The messages.
     * @return This object.
     */
    public GetMessagesForSearchJobResponse withMessages(List<LogMessage> messages) {
        setMessages(messages);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(128);
        result.append("fields: ");
        result.append(fields);
        result.append(", message count: '");
        result.append(messages.size());
        result.append("'");
        return result.toString();
    }
}
