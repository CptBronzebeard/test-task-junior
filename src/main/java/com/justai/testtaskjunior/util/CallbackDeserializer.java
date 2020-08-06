package com.justai.testtaskjunior.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.justai.testtaskjunior.model.callbacks.VkJsonCallback;

import java.io.IOException;

public class CallbackDeserializer extends StdDeserializer<VkJsonCallback> {
    public CallbackDeserializer() {this (null);}
    public CallbackDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public VkJsonCallback deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String type = jsonNode.get("type").asText();
        JsonNode object = jsonNode.get("object");
        Integer groupId = jsonNode.get("group_id").asInt();
        String eventId = jsonNode.get("event_id").asText();
        return new VkJsonCallback(type, object, groupId, eventId);
    }
}
