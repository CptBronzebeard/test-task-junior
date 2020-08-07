package com.justai.testtaskjunior;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justai.testtaskjunior.model.messages.VkMessage;
import com.justai.testtaskjunior.service.BasicMessageNewHandlerService;
import com.justai.testtaskjunior.util.HttpClientWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
@PropertySource("classpath:test.properties")
class TestTaskJuniorApplicationTests {
    private final static String jsonGroupString = "{\n" +
            "            \"date\": 1596739399,\n" +
            "            \"from_id\": 1,\n" +
            "            \"id\": 0,\n" +
            "            \"out\": 0,\n" +
            "            \"peer_id\": 2000000001,\n" +
            "            \"text\": \"test\",\n" +
            "            \"conversation_message_id\": 26,\n" +
            "            \"fwd_messages\": [],\n" +
            "            \"important\": false,\n" +
            "            \"random_id\": 0,\n" +
            "            \"attachments\": [],\n" +
            "            \"is_hidden\": false\n" +
            "        }";
    private final static String jsonPersonalString = "{\n" +
            "            \"date\": 1596739399,\n" +
            "            \"from_id\": 1,\n" +
            "            \"id\": 0,\n" +
            "            \"out\": 0,\n" +
            "            \"peer_id\": 2,\n" +
            "            \"text\": \"test\",\n" +
            "            \"conversation_message_id\": 26,\n" +
            "            \"fwd_messages\": [],\n" +
            "            \"important\": false,\n" +
            "            \"random_id\": 0,\n" +
            "            \"attachments\": [],\n" +
            "            \"is_hidden\": false\n" +
            "        }";
    @MockBean
    HttpClientWrapper httpClientWrapper;
    @Autowired
    ApplicationContext context;
    VkMessage groupMessage;
    VkMessage personalMessage;
    BasicMessageNewHandlerService messageService;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        groupMessage = new ObjectMapper().readValue(jsonGroupString, VkMessage.class);
        personalMessage = new ObjectMapper().readValue(jsonPersonalString, VkMessage.class);
        Mockito.when(httpClientWrapper.post(anyString())).then(
                returnsFirstArg());
        messageService = context.getBean(BasicMessageNewHandlerService.class);
    }

    String targetGroup = "https://api.vk.com/method/messages.send?peer_id=2000000001&user_ids=1&chat_id=1&message=TEST_PREFIX_test&random_id=1&access_token=TEST&v=5.TEST";

    @Test
    void MessageSendGroupToUrl() throws UnsupportedEncodingException {
        Assertions.assertEquals(targetGroup, URLDecoder.decode(messageService.handle(groupMessage), "UTF-8"));
    }

    String targetPersonal = "https://api.vk.com/method/messages.send?peer_id=1&user_id=1&message=TEST_PREFIX_test&random_id=1&access_token=TEST&v=5.TEST";

    @Test
    void MessageSendPersonalToUrl() throws UnsupportedEncodingException {
        Assertions.assertEquals(targetPersonal, URLDecoder.decode(messageService.handle(personalMessage), "UTF-8"));
    }

}
