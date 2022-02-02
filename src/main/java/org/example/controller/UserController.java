package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.DepartmentFacade;
import org.example.facade.UserFacade;
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

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserFacade userFacade;
    private final DepartmentFacade departmentFacade;

    @PostMapping(value = "/{departmentId}")
    private UserDTO save(@RequestBody UserDTORegistration userDTORegistration, @PathVariable("departmentId") Integer departmentId) {
        userDTORegistration.setDepartment(departmentFacade.findById(departmentId));
        return userFacade.save(userDTORegistration);
    }

    @GetMapping("/{id}")
    private UserDTO findById(@PathVariable("id") Integer id) {
        UserDTO user = userFacade.findById(id);
        return user;
    }

    @GetMapping(value = "/")
    private List<UserDTO> getAll() {
        List<UserDTO> users = userFacade.findAll();
        return users;
    }

    @PutMapping("/{id}")
    private UserDTO update(@PathVariable("id") Integer id, @RequestBody UserDTO dto)
            throws ResourceNotFoundException {
        UserDTO updated = userFacade.update(id, dto);
        return updated;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        userFacade.deleteById(id);
        return"User: " + id + "deleted successfully";
    }
}



