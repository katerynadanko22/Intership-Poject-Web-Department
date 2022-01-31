package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.dto.UserDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.ValidationException;
import org.example.facade.DepartmentFacade;
import org.example.facade.UserFacade;
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

@Slf4j
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private DepartmentFacade departmentFacade;


    @PostMapping(value = "/save/{departmentId}")
    private ResponseEntity<String> saveDepartment(@RequestBody UserDTO user,
                                                  @PathVariable("departmentId") Integer departmentId) throws ValidationException {
        user.setDepartmentDTO(departmentFacade.findById(departmentId));
        UserDTO savedUser = userFacade.save(user);
        return ResponseEntity.ok("User: " + savedUser + " saved successfully");
    }

    @GetMapping("/find/{id}")
    private ResponseEntity<String> findUserById(@PathVariable("id") Integer id) {
        UserDTO user = userFacade.findById(id);
        return ResponseEntity.ok("User with id: " + id + " has name: " + user);
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<String> getUserById(@PathVariable("id") Integer id) {
        UserDTO user = userFacade.getById(id);
        return ResponseEntity.ok("User with id: " + id + " has name: " + user);
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllUsers() {
        List<UserDTO> users = userFacade.findAll();
        return ResponseEntity.ok("Users: " + users);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updatePutProjectDTOById(@PathVariable("id") Integer id,
                                                           @RequestBody UserDTO dto)
            throws ResourceNotFoundException {
        UserDTO updated = userFacade.update(id, dto);
        return ResponseEntity.ok("User " + updated + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id) {
        userFacade.deleteById(id);
        return ResponseEntity.ok("User: " + id + "deleted");
    }
}



