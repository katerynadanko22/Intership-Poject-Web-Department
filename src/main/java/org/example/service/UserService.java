package org.example.service;


import org.example.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findById(Integer id);

    User update(Integer id, User updatedUser);

    void deleteById(Integer id);

}
