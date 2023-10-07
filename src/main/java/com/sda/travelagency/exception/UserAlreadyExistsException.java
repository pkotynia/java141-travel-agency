package com.sda.travelagency.exception;

public class UserAlreadyExistsException extends RuntimeException{
    /**
     * Exception which is thrown when new User username already exists in database
     * @param message
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
