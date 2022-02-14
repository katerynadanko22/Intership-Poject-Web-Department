package org.example.exception;

public class InValidCSVException extends RuntimeException{
    public InValidCSVException(String message) {
        super(message);
    }

    public InValidCSVException(String message, Throwable cause) {
        super(message, cause);
    }
}
