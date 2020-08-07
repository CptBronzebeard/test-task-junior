package com.justai.testtaskjunior.controller;

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

    @Autowired
    public CallbackController(CallbackHandlerService callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    @RequestMapping(value = "${vk.callback}", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String handleCallback(@RequestBody String incomingMessage) throws IOException, ErroneousResponseException {
        return callbackHandler.handleCallback(incomingMessage);
    }
}
