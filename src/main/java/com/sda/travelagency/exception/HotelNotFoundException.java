package com.sda.travelagency.exception;

public class HotelNotFoundException extends RuntimeException{
    /**
     * Exception which is thrown when Hotel object is not found in database
     * @param message
     */
    public HotelNotFoundException(String message) {
        super(message);
    }
}
