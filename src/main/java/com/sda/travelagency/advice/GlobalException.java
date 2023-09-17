package com.sda.travelagency.advice;

import com.sda.travelagency.exception.HotelCantBeDeletedException;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.exception.OfferNotFoundException;
import com.sda.travelagency.exception.ValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(HotelNotFoundException.class)
    public ProblemDetail handleHotelNotFoundException(HotelNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HotelCantBeDeletedException.class)
    public ProblemDetail handleHotelCantBeDeletedException(HotelCantBeDeletedException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ProblemDetail handleOfferNotFoundException(OfferNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleValidationException(ValidationException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
