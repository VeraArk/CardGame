package org.example.myjavaproappgame.service.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException (String message){
        super(message);
    }
}