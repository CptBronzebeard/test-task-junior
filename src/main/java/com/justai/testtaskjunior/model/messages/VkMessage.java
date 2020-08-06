package com.justai.testtaskjunior.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VkMessage implements Serializable {
    private Integer id;
    @JsonProperty("date")
    private Integer unixTimeSent;
    @JsonProperty("peer_id")
    private Integer peerId;
    @JsonProperty("from_id")
    private Integer fromId;
    private String text;
    @JsonProperty("random_id")
    private Integer randomId;
    private String ref;
    @JsonProperty("ref_source")
    private String refSource;
    private List<VkAttachment> attachments;
    private Boolean important;
    private VkGeo geo;
    private String payload;
    private VkBotKeyboard keyboard;
    @JsonProperty("fwd_messages")
    private List<VkMessage> fwdMessages;
    @JsonProperty("reply_message")
    private VkMessage replyMessage;
    private VkChatAction action;
    //Old API versions compatibility field
    private Integer out;
    //FIXME Below fields are NOT LISTED in VK API, yet are being sent, as of version 5.122
    @JsonProperty("conversation_message_id")
    private Integer conversationMessageId;
    @JsonProperty("is_hidden")
    private Boolean isHidden;
}
