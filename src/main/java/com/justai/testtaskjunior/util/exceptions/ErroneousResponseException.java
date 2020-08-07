package com.justai.testtaskjunior.util.exceptions;

public class ErroneousResponseException extends Exception {
    public ErroneousResponseException(String error) {
        super("Received following error from the server: " + error);
    }
}
