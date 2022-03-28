package org.example.exception;

public class WritingReportException extends RuntimeException{
    public WritingReportException() {
    }

    public WritingReportException(String message) {
        super(message);
    }

    public WritingReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
