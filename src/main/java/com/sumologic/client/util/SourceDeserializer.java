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
package com.sumologic.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sumologic.client.collectors.model.Source;
import com.sumologic.client.collectors.model.SourceType;

import java.io.IOException;

public class SourceDeserializer extends StdDeserializer<Source> {

    public SourceDeserializer() {
        super(Source.class);
    }

    @Override
    public Source deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {

        Source source = JacksonUtils.MAPPER.readValue(jsonParser, Source.class);
        Class<? extends Source> sourceClass = SourceType.getSourceClass(source.getSourceType());

        if (sourceClass != null) {
            String sourceJson = JacksonUtils.MAPPER.writeValueAsString(source);
            return JacksonUtils.MAPPER.readValue(sourceJson, sourceClass);
        }
        return source;
    }
}
