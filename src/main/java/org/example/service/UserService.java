package org.example.service;


import org.example.entity.ResetPassword;
import org.example.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    User resetPassword(ResetPassword resetPassword);

    List<User> findAll();

    User findById(Integer id);

    User update(User userNew, Integer id);

    User updateDepartment(Integer newDepartmentId, Integer id);

    void deleteById(Integer id);

}
