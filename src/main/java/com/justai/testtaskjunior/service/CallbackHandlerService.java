package com.justai.testtaskjunior.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justai.testtaskjunior.model.callbacks.VkJsonCallback;
import com.justai.testtaskjunior.model.messages.VkMessage;
import com.justai.testtaskjunior.util.exceptions.ErroneousResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CallbackHandlerService {
    private ObjectMapper objectMapper;
    private BasicMessageNewHandlerService basicMessageNewHandlerService;

    @Autowired
    public CallbackHandlerService(ObjectMapper objectMapper, BasicMessageNewHandlerService basicMessageNewHandlerService) {
        this.objectMapper = objectMapper;
        this.basicMessageNewHandlerService = basicMessageNewHandlerService;
    }

    @Value("${vk.api.confirmation-string}")
    private String confirmationString;
    @Value("${vk.api.expected-group-id}")
    private Integer expectedGroupId;

    public String handleCallback(String incomingJson) throws IOException, ErroneousResponseException {
        VkJsonCallback callback;
        callback = objectMapper.readValue(incomingJson, VkJsonCallback.class);
        switch (callback.getType()) {
            case "confirmation":
                return callback.getGroupId().equals(expectedGroupId) ? confirmationString : "error: Unexpected group id";
            case "message_new":
                handleMessageNew(callback.getObject());
            default:
                return "ok";
        }
    }


    private void handleMessageNew(JsonNode object) throws IOException, ErroneousResponseException {
        JsonNode messageNode = object.get("message");
        VkMessage message = objectMapper.treeToValue(messageNode, VkMessage.class);
        basicMessageNewHandlerService.handle(message);
    }
}
