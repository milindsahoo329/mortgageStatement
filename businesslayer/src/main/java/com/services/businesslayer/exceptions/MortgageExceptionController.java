package com.services.businesslayer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MortgageExceptionController {
    @ExceptionHandler(value = OfferDateLessException.class)
    public ResponseEntity<Object> exception(OfferDateLessException exception) {
        return new ResponseEntity<>("Offer date cannot be processed", HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = CustomNotFoundException.class)
    public ResponseEntity<Object> exception(CustomNotFoundException exception) {
        return new ResponseEntity<>("Business layer called the wrong end point", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomServerException.class)
    public ResponseEntity<Object> exception(CustomServerException exception) {
        return new ResponseEntity<>("Data layer threw an error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
