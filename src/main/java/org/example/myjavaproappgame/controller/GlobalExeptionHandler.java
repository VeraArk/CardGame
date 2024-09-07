package org.example.myjavaproappgame.controller;

import jakarta.validation.ConstraintViolationException;
import org.example.myjavaproappgame.service.exception.AlreadyExistException;
import org.example.myjavaproappgame.service.exception.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handlerAlreadyExistException(AlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception){
        StringBuilder responseMessage = new StringBuilder();

        exception.getConstraintViolations().forEach(
                constraintViolation -> {
                    String message = constraintViolation.getMessage();
                    responseMessage.append(message).append("\n");
                }
        );

        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        StringBuilder responseMessage = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            responseMessage.append(fieldName + " : " + message).append("\n");
        });
        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }

}

