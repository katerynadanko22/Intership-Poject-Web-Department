package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.DuplicateEntityException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Nullable
    @Override
    public User save(User user) {
        log.info("user start to save with id{} ", user.getId());
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new DuplicateEntityException("User already exist");
        }
        log.error("user already exist with id{} ", user.getId());
        return userRepository.save(user);
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
    public User update(Integer id, User userNew) {
        log.info("user start to update  by id {} ", id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user in BD"));
        user.setFirstName(userNew.getFirstName());
        user.setLastName(userNew.getLastName());
        user.setJobTitle(userNew.getJobTitle());
        user.setDepartment(userNew.getDepartment());
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        user.setPassword(userNew.getPassword());
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
