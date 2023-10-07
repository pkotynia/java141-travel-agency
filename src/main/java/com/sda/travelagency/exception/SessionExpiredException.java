package com.sda.travelagency.exception;

public class SessionExpiredException extends RuntimeException{
    /**
     * Exception which is thrown when Username util class is called without active session
     * @param message
     */
    public SessionExpiredException(String message) {
        super(message);
    }
}