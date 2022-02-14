package org.example.exception;

public class CsvValidationException extends RuntimeException{
    public CsvValidationException(String message) {
        super(message);
    }
}
