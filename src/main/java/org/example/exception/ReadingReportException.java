package org.example.exception;

public class ReadingReportException extends RuntimeException{
    public ReadingReportException() {
    }

    public ReadingReportException(String message) {
        super(message);
    }

    public ReadingReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
