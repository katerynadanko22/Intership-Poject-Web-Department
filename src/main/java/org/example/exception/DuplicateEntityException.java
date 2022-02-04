package org.example.exception;

import lombok.Data;

@Data
public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String message) {
        super(message);
    }
}