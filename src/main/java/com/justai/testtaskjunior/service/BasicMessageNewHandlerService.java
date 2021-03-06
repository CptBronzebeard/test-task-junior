package com.justai.testtaskjunior.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justai.testtaskjunior.model.messages.VkMessage;
import com.justai.testtaskjunior.model.queries.MessageSendQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class BasicMessageNewHandlerService implements ObjectHandler<VkMessage> {
    private ApplicationContext beanFactory;
    private ObjectMapper objectMapper;
    @Value("${bot.prefix}")
    private String prefix;

    @Autowired
    public BasicMessageNewHandlerService(
            ApplicationContext context,
            ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.beanFactory = context;
    }

    public String handle(VkMessage message) {
        MessageSendQuery msgSendQuery = beanFactory.getBean(MessageSendQuery.class);
        String result = "";
        if (message.getPeerId() >= 2000000000)
            result = sendToGroup(message, msgSendQuery);
        else
            result = sendToPersonal(message, msgSendQuery);
        return result;
    }

    private String sendToGroup(VkMessage message, MessageSendQuery query) {
        query.peerId(message.getPeerId());
        query.userIds(Collections.singletonList(message.getFromId()));
        query.chatId(message.getPeerId() - 2000000000);
        commonOps(message, query);
        return query.execute();
    }

    private String sendToPersonal(VkMessage message, MessageSendQuery query) {
        query.peerId(message.getFromId());
        query.userId(message.getFromId());
        commonOps(message, query);
        return query.execute();
    }

    private String composeMessage(String text) {
        return prefix + text;
    }

    private void commonOps(VkMessage message, MessageSendQuery query) {
        query.message(composeMessage(message.getText()));
        query.randomId();
    }
}
