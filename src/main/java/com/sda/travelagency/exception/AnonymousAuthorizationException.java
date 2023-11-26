package com.sda.travelagency.exception;

public class AnonymousAuthorizationException extends RuntimeException{
    /**
     * Exception which is thrown when Username util class is called without active session
     * @param message
     */
    public AnonymousAuthorizationException(String message) {
        super(message);
    }
}