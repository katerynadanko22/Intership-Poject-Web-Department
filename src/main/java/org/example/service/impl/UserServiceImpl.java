package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.DuplicateUserException;
import org.example.repository.DepartmentRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public User save(User user, Integer departmentId) {
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                user.getId(), user.getFirstName(), user.getLastName()));
        if (departmentId == null) {
            throw new RuntimeException("Required parameters: departmentId");
        }
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new DuplicateUserException(user);
            }
        }
//        if (departmentRepository.findById(departmentId).isPresent())
            user.setDepartment(departmentRepository.getById(departmentId));
        return userRepository.save(user);
    }
    @Override
    public User save(User user) {
        log.info(String.format("user.save {id = %d}", user.getId()));
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
        return userRepository.findById(id);
    }

    @Override
    public User update(User updatedUser) {
        User user = userRepository.getById(updatedUser.getId());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setJobTitle(updatedUser.getJobTitle());
        user.setDepartment(updatedUser.getDepartment());
        user.setEmail(updatedUser.getEmail());
        if (!updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }
        user.setPassword(updatedUser.getPassword());
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
