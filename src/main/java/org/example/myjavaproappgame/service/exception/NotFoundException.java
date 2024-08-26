package org.example.myjavaproappgame.service.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message){
      super(message);
  }
}
