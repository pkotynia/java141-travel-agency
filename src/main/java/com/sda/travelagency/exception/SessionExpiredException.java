package com.sda.travelagency.exception;

public class SessionExpiredException extends RuntimeException{

    public SessionExpiredException(String message) {
        super(message);
    }
}