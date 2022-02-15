package org.example.service;

import org.example.entity.ResetPassword;
import org.example.entity.User;

import java.util.List;

public interface AuthorizationService {

    User registerUser(User user);

    List<User> registerAll(List<User> users);

    User resetPassword(ResetPassword resetPassword);
}
