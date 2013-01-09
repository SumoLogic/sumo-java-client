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
