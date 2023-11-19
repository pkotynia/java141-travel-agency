package com.sda.travelagency.exception;

public class OfferNotFoundException extends RuntimeException{
    /**
     * Exception which is thrown when City object is not found in database
     * @param message
     */
    public OfferNotFoundException(String message) {
        super(message);
    }
}
