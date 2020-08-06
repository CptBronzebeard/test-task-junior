package com.justai.testtaskjunior.service;

import com.justai.testtaskjunior.model.messages.VkMessage;
import org.springframework.stereotype.Service;

@Service
public class MessageHandlerService implements ObjectHandler<VkMessage> {
    public void handle(VkMessage message) {
        System.out.println(message.getText());
    }
}
