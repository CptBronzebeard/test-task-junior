package com.justai.testtaskjunior.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justai.testtaskjunior.service.CallbackHandlerService;
import com.justai.testtaskjunior.util.exceptions.ErroneousResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class CallbackController {
    private final CallbackHandlerService callbackHandler;
    private final ObjectMapper objectMapper;

    @Autowired
    public CallbackController(CallbackHandlerService callbackHandler, ObjectMapper objectMapper) {
        this.callbackHandler = callbackHandler;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "${vk.callback}", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String handleCallback(@RequestBody String incomingMessage) throws IOException, ErroneousResponseException {
        String result = callbackHandler.handleCallback(incomingMessage);
        JsonNode response = objectMapper.readTree(result);
        if (response.has("error")) throw new ErroneousResponseException(response.get("Error").asText());
        return result;
    }
}
