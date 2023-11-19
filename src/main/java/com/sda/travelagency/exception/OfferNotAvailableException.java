package com.sda.travelagency.exception;

public class OfferNotAvailableException extends RuntimeException{

    /**
     * Exception which is thrown when user wants to reserve already reserved Offer object.
     * @param message
     */
    public OfferNotAvailableException(String message) {
        super(message);
    }
    }
