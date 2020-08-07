package com.justai.testtaskjunior.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;


@ControllerAdvice
public class SimpleExceptionHandler {
    private Logger logger;

    @Autowired
    public SimpleExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JsonParseException.class, JsonMappingException.class})
    public String parseError() {
        logger.error("JSON parsing error");
        return "callback JSON parsing error";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public String ioError() {
        logger.error("Unknown IO error");
        return "Unknown IO error";
    }
}
