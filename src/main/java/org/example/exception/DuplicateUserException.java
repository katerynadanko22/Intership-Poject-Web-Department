package org.example.exception;

import lombok.Data;

@Data
public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String message) {
      super(message);
    }
}