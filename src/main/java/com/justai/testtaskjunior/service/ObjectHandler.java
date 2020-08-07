package com.justai.testtaskjunior.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justai.testtaskjunior.util.exceptions.ErroneousResponseException;

public interface ObjectHandler<T> {
    String handle(T object) throws JsonProcessingException, ErroneousResponseException;
}
