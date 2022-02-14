package org.example.service.impl;

import org.example.entity.ResetPassword;
import org.example.entity.User;
import org.example.exception.DuplicateEntityException;
import org.example.exception.InvalidPasswordException;
import org.example.repository.UserRepository;
import org.example.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthorizationServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new DuplicateEntityException(String.format("User with email=%s already exists", user.getEmail()));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> registerAll(List<User> users) {
        List<User> saved = new ArrayList<>();
        for (User u : users) {
            saved.add(registerUser(u));
        }
        return saved;
    }

    @Override
    @Transactional
    public User resetPassword(ResetPassword resetPassword) {
        User user = userRepository.findByEmail(resetPassword.getEmail())
                .orElseThrow(() -> new NoSuchElementException(String.format("Employee with username=%s not found",
                        resetPassword.getEmail()))
                );
        if (!encoder.matches(resetPassword.getPasswordOld(), user.getPassword())) {
            throw new InvalidPasswordException("You entered incorrect old password");
        }
        user.setPassword(encoder.encode(resetPassword.getPasswordNew()));
        return user;
    }
}