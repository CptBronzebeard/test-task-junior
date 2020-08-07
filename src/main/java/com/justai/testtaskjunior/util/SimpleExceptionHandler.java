package com.justai.testtaskjunior.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.justai.testtaskjunior.util.exceptions.ErroneousResponseException;
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
    public String parseError(Exception e) {
        logger.error("JSON parsing error: " + e);
        return "JSON parsing error";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public String ioError(IOException e) {
        logger.error("Unknown IO error: " + e);
        return "Unknown IO error";
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ErroneousResponseException.class)
    public String responseError(ErroneousResponseException e) {
        logger.error(e.toString());
        return e.toString();
    }
}
