package com.justai.testtaskjunior.model.queries;

import com.justai.testtaskjunior.util.HttpClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class MessageSendQuery extends ApiQuery {
    @Value("${random.int}")
    private int randomId;

    @Autowired
    public MessageSendQuery(@Value("${vk.api.address}") String url,
                            HttpClientWrapper httpClientWrapper) {
        super(url, httpClientWrapper);
        builder.path("messages.send");
    }

    public MessageSendQuery userId(Integer userId) {
        builder.queryParam("user_id", userId);
        return this;
    }

    public MessageSendQuery randomId() {
        builder.queryParam("random_id", randomId);
        return this;
    }

    public MessageSendQuery peerId(Integer peerId) {
        builder.queryParam("peer_id", peerId);
        return this;
    }

    public MessageSendQuery domain(String domain) {
        builder.queryParam("domain", domain);
        return this;
    }

    public MessageSendQuery userIds(List<Integer> userIds) {
        builder.queryParam("user_ids", userIds);
        return this;
    }

    public MessageSendQuery chatId(Integer chatId) {
        builder.queryParam("chat_id", chatId);
        return this;
    }

    public MessageSendQuery message(String message) {
        builder.queryParam("message", message);
        return this;
    }
}
