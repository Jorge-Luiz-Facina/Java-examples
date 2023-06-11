package com.spi.service.two.exception;


import javax.ws.rs.WebApplicationException;

public class CustomException extends WebApplicationException {
    private final String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}