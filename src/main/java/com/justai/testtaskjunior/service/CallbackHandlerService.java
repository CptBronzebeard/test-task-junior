package com.justai.testtaskjunior.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justai.testtaskjunior.model.callbacks.VkJsonCallback;
import com.justai.testtaskjunior.model.messages.VkMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CallbackHandlerService {
    private ObjectMapper objectMapper;
    private MessageHandlerService messageHandlerService;
    @Autowired
    public CallbackHandlerService(ObjectMapper objectMapper, MessageHandlerService messageHandlerService) {
        this.objectMapper = objectMapper;
        this.messageHandlerService = messageHandlerService;
    }

    @Value("${vk.api.confirmation-string}")
    private String confirmationString;
    @Value("${vk.api.expected-group-id}")
    private Integer expectedGroupId;

    public String handleCallback(String incomingJson) throws IOException {
        VkJsonCallback callback;
        callback = objectMapper.readValue(incomingJson, VkJsonCallback.class);
        switch (callback.getType()) {
            case "confirmation":
                return callback.getGroupId().equals(expectedGroupId) ? confirmationString : "error: Unexpected group id";
            case "message_new":
                handleMessage(callback.getObject());
            default:
                return "ok";
        }
    }
    @Async
    void handleMessage(JsonNode object) throws IOException {
        JsonNode messageNode = object.get("message");
        VkMessage message = objectMapper.treeToValue(messageNode, VkMessage.class);
        messageHandlerService.handle(message);
    }
}
