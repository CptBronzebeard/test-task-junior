package com.justai.testtaskjunior.model.callbacks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.justai.testtaskjunior.util.CallbackDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = CallbackDeserializer.class)
public class VkJsonCallback {
    private String type;
    private JsonNode object;
    private Integer groupId;
    private String eventId;

}
