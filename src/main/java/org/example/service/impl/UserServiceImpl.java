package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ResetPassword;
import org.example.entity.User;
import org.example.exception.DuplicateEntityException;
import org.example.exception.InvalidPasswordException;
import org.example.repository.DepartmentRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public User registerUser(User user) {
        log.info("user start to save with id={} ", user.getId());
        log.error("user already exist with id={} ", user.getId());
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new DuplicateEntityException("User with id={} already exist" + user.getId());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User resetPassword(ResetPassword resetPassword) {
        User user = userRepository.findByEmail(resetPassword.getEmail())
                .orElseThrow(() -> new NoSuchElementException(String.format("User with email={} not found",
                        resetPassword.getEmail()))
                );
        if (!passwordEncoder.matches(resetPassword.getPasswordOld(), user.getPassword())) {
            throw new InvalidPasswordException("You entered incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(resetPassword.getPasswordNew()));
        return user;
    }

    @Override
    public List<User> findAll() {
        log.info("user start to get All");
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        log.info("user start to get by id {} ", id);
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user in BD"));
    }

    @Override
    public User update(User userNew, Integer id) {
        log.info("user start to update  by id {} ", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user in BD"));
        user.setFirstName(userNew.getFirstName());
        user.setLastName(userNew.getLastName());
        user.setJobTitle(userNew.getJobTitle());
        user.setEmail(userNew.getEmail());
        return userRepository.save(user);
    }
    @Override
    public User updateDepartment(Integer newDepartmentId, Integer id) {
        log.info("user start to update Department by id {} ", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user in BD"));
        user.setDepartment(Optional.of(departmentRepository.findById(newDepartmentId)
                .orElseThrow(() -> new NoSuchElementException("No such Department in BD"))).get());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("user start to delete  by id {} ", id);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
