package org.example.exception;

import lombok.Data;
import org.example.entity.User;

@Data
public class DuplicateUserException extends RuntimeException{
    private final User user;

  public DuplicateUserException(User user) {
    this.user = user;
  }
}