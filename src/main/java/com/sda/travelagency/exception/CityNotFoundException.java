package com.sda.travelagency.exception;


public class CityNotFoundException extends RuntimeException{
    /**
     * Exception which is thrown when City object is not found in database
     * @param message
     */
    public CityNotFoundException(String message) {
        super(message);
    }
}
