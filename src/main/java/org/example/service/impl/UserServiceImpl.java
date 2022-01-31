package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.DuplicateUserException;
import org.example.exception.EmptyInputException;
import org.example.modelmapper.UserMapper;
import org.example.repository.DepartmentRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public User save(User user) {
        log.info(String.format("user.save {id = %d}", user.getId()));
        if (user.getFirstName().isEmpty() || user.getFirstName().length() == 0) {
            throw new EmptyInputException("Input fields are empty");
        }
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new DuplicateUserException("User already exist");
            }
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.info("user.getAll: " + userRepository.findAll());
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        log.info("user.get by id: " + userRepository.getById(id));
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return userRepository.findById(id);
    }

    @Override
    public User update(Integer id, User updatedUser) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        User user = userRepository.findById(id).get();
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setJobTitle(updatedUser.getJobTitle());
        user.setDepartment(updatedUser.getDepartment());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        userRepository.deleteById(id);
    }
}
