package org.example.exception;

public class CSVReadingException extends RuntimeException {
    public CSVReadingException(String message, Exception e) {
        super(message);
    }
}
