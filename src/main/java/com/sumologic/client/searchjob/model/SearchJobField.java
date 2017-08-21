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

public class SearchJobField {

    private String name;
    private String fieldType;
    private boolean keyField;

    /**
     * Returns the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name.
     *
     * @param name The name.
     * @return The name.
     */
    public SearchJobField withName(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the fieldType.
     *
     * @return The fieldType.
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the fieldType.
     *
     * @param fieldType The fieldType.
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Sets the field type.
     *
     * @param fieldType The field type.
     * @return This object.
     */
    public SearchJobField withFieldType(String fieldType) {
        setFieldType(fieldType);
        return this;
    }

    /**
     * Returns whether this is a key field.
     *
     * @return Whether this is a key field.
     */
    public boolean isKeyField() {
        return keyField;
    }

    /**
     * Sets whether this is a key field.
     *
     * @param isKeyField Whether this is a key field.
     */
    public void setKeyField(boolean isKeyField) {
        this.keyField = isKeyField;
    }

    /**
     * Sets whether this is a key field.
     *
     * @param isKeyField Whether this is a key field.
     * @return This object.
     */
    public SearchJobField withKeyField(boolean isKeyField) {
        setKeyField(isKeyField);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("name: '");
        result.append(name);
        result.append("', fieldType: '");
        result.append(fieldType);
        result.append("', keyField: '");
        result.append(keyField);
        result.append("'");
        return result.toString();
    }
}