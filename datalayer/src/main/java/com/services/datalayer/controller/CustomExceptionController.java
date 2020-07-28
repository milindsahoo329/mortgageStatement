package com.services.datalayer.controller;

import com.services.datalayer.exceptions.RecordNotFoundException;
import com.services.datalayer.exceptions.StorageExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler(value = StorageExceededException.class)
    public ResponseEntity<Object> exception(StorageExceededException exception) {
        return new ResponseEntity<>("Data Storage out of bound", HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>("Record not found", HttpStatus.NO_CONTENT);
    }

}
