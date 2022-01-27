package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/save/{departmentId}")
    private ResponseEntity<String> saveDepartment(@RequestBody User user, @PathVariable("departmentId") Integer departmentId) {
        User savedUser = userService.save(user, departmentId);
        return ResponseEntity.ok("User: " + savedUser + " saved successfully");
    }

    @GetMapping("/find/{id}")
    private ResponseEntity<String> findUserById(@PathVariable("id") Integer id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok("User with id: " + id + " has name: " + user.get());
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<String> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.ok("User with id: " + id + " has name: " + user);
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok("Users: " + users);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateUserById(@RequestBody User userNew)
            throws ResourceNotFoundException {
        User user = userService.findById(userNew.getId()).get();
        user.setFirstName(userNew.getFirstName());
        user.setDepartment(userNew.getDepartment());
        user.setLastName(userNew.getLastName());
        user.setJobTitle(userNew.getJobTitle());
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        final User updatedUser = userService.save(user);
        return ResponseEntity.ok("User " + updatedUser + " updated successfully");
    }

    @PostMapping("/")
    private ResponseEntity<String> updateUser(@RequestBody User userNew)
            throws ResourceNotFoundException {
        User user = userService.findById(userNew.getId()).get();
        user.setFirstName(userNew.getFirstName());
        final User updatedUser = userService.update(userNew);
        return ResponseEntity.ok("User " + updatedUser + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteUserById(@PathVariable("id") Integer id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}



