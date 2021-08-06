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
 * A source that reads from local files matching a path expression.
 */
public class LocalFileSource extends Source {

    private static String PATH_EXPRESSION = "pathExpression";
    private static String BLACKLIST = "blacklist";
    private static String DENYLIST = "denylist";

    public LocalFileSource() {
        setSourceType(SourceType.LOCAL_FILE.getType());
    }

    /**
     * Returns the path expression.
     *
     * @return The path expression.
     */
    public String getPathExpression() {
        return getProperty(PATH_EXPRESSION);
    }

    /**
     * Sets the path expression.
     */
    public void setPathExpression(String pathExpression) {
        setProperty(PATH_EXPRESSION, pathExpression);
    }

    /**
     * Returns the blacklist.
     *
     * @return The blacklist.
     */
    public List<String> getBlacklist() {
        return getProperty(BLACKLIST);
    }

    /**
     * Sets the blacklist.
     */
    public void setBlacklist(List<String> blacklist) {
        setProperty(BLACKLIST, blacklist);
    }

    /**
     * Returns the denylist.
     *
     * @return The denylist.
     */
    public List<String> getDenylist() {
        return getProperty(DENYLIST);
    }

    /**
     * Sets the denylist.
     */
    public void setDenylist(List<String> denylist) {
        setProperty(DENYLIST, denylist);
    }
}
