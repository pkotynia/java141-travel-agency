package com.sda.travelagency.exception;

public class HotelCantBeDeletedException extends RuntimeException{
    /**
     * Exception which is thrown when user wants to delete Hotel object which has associated offers
     * @param message
     */
    public HotelCantBeDeletedException(String message) {
        super(message);
    }
}
