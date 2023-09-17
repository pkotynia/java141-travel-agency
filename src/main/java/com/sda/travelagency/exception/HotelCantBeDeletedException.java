package com.sda.travelagency.exception;

public class HotelCantBeDeletedException extends RuntimeException{
    public HotelCantBeDeletedException(String message) {
        super(message);
    }
}
