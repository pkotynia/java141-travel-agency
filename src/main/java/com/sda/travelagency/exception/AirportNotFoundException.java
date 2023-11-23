package com.sda.travelagency.exception;


public class AirportNotFoundException extends RuntimeException{
    /**
     * Exception which is thrown when City object is not found in database
     * @param message
     */
    public AirportNotFoundException(String message) {
        super(message);
    }
}
