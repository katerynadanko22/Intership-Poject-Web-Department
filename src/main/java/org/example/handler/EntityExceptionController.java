package org.example.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.DuplicateUserException;
import org.example.exception.EmptyInputException;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
@EnableWebMvc
public class EntityExceptionController {

    @ExceptionHandler(EmptyInputException.class )
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException e) {
        return new ResponseEntity<String>("Input field is empty", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class )
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<String>("No such element found!", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoSuchElementException.class )
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>("No such element found!", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateUserException.class )
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException e) {
        return new ResponseEntity<String>("User already exist!", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ValidationException.class )
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        return new ResponseEntity<String>("Email ", HttpStatus.BAD_REQUEST);
    }
}
