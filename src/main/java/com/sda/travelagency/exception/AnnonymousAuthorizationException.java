package com.sda.travelagency.exception;

public class AnnonymousAuthorizationException extends RuntimeException{
    /**
     * Exception which is thrown when Username util class is called without active session
     * @param message
     */
    public AnnonymousAuthorizationException(String message) {
        super(message);
    }
}