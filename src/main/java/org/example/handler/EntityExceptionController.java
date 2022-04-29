package org.example.handler;

import lombok.AllArgsConstructor;
import org.example.exception.DuplicateEntityException;
import org.example.exception.EmptyInputException;
import org.example.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.NoSuchElementException;

@AllArgsConstructor
@ControllerAdvice
@EnableWebMvc
public class EntityExceptionController {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException e) {
        return new ResponseEntity<String>("Input field is empty", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<String>("No such element found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>("No such element found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateEntityException e) {
        return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateUserException(DataIntegrityViolationException e) {
        return new ResponseEntity<String>("BAD_REQUEST!", HttpStatus.BAD_REQUEST);
    }
}
