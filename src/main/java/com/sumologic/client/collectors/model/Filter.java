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

public class Filter {

    public static String EXCLUDE = "Exclude";
    public static String INCLUDE = "Include";
    public static String HASH = "Hash";
    public static String MASK = "Mask";

    private String filterType;
    private String name;
    private String regexp;
    private String mask;

    /**
     * Returns the filter type.
     *
     * @return The filter type.
     */
    public String getFilterType() {
        return filterType;
    }

    /**
     * Sets the filter type.
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

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
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the regular expression.
     *
     * @return The regular expression.
     */
    public String getRegexp() {
        return regexp;
    }

    /**
     * Sets the regular expression.
     */
    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    /**
     * Returns the mask.
     *
     * @return The mask.
     */
    public String getMask() {
        return mask;
    }

    /**
     * Sets the mask.
     */
    public void setMask(String mask) {
        this.mask = mask;
    }
}
