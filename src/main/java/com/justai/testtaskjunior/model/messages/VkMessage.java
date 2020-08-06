package com.justai.testtaskjunior.model.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VkMessage {
    private Integer id;
    private Integer unixTimeSent;
    private Integer peerId;
    private Integer fromId;
    private String text;
    private Integer randomId;
    private String ref;
    private String refSource;
    private List<VkAttachment> attachments;
    private Boolean important;
    private VkGeo geo;
    private String payload;
    private VkBotKeyboard keyboard;
    private List<VkMessage> fwdMessages;
    private VkMessage replyMessage;
    private VkChatAction action;
}
